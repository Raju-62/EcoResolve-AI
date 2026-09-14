const API='http://localhost:8080/api';
const $=id=>document.getElementById(id);
const state= {
  token:localStorage.getItem('ecoresolve_token'),user:JSON.parse(localStorage.getItem('ecoresolve_user')||'null')
}
;
function show(id) {
  document.querySelectorAll('main section').forEach(s=>s.classList.add('hidden'));
  const el=document.getElementById(id);
  if(el)el.classList.remove('hidden');
  window.scrollTo( {
    top:0,behavior:'smooth'
  }
  );
  if(id==='dashboard')loadStudent();
  if(id==='newComplaint')loadLocations();
  if(id==='admin')loadAdmin()
}
async function api(path,opts= {
}
) {
  opts.headers= {
    ...(opts.headers|| {
    }
    ),'Content-Type':'application/json'
  }
  ;
  if(state.token)opts.headers.Authorization='Bearer '+state.token;
  const r=await fetch(API+path,opts);
  let d=null;
  try {
    d=await r.json()
  }
  catch {
  }
  if(!r.ok)throw new Error(d?.message||'Request failed');
  return d
}
async function register(e) {
  e.preventDefault();
  const m=document.getElementById('regMsg');
  try {
    await api('/auth/register', {
      method:'POST',body:JSON.stringify( {
        name:$('rname').value,email:$('remail').value,password:$('rpassword').value
      }
      )
    }
    );
    m.textContent='Account created. You can now log in.';
    setTimeout(()=>show('login'),800)
  }
  catch(x) {
    m.textContent=x.message
  }
}
async function login(e) {
  e.preventDefault();
  const m=document.getElementById('loginMsg');
  try {
    const d=await api('/auth/login', {
      method:'POST',body:JSON.stringify( {
        email:$('lemail').value,password:$('lpassword').value
      }
      )
    }
    );
    state.token=d.token;
    state.user=d.user;
    localStorage.setItem('ecoresolve_token',d.token);
    localStorage.setItem('ecoresolve_user',JSON.stringify(d.user));
    m.textContent='';
    show(d.user.role==='ADMIN'?'admin':'dashboard')
  }
  catch(x) {
    m.textContent=x.message
  }
}
function logout() {
  state.token=null;
  state.user=null;
  localStorage.clear();
  show('home')
}
async function loadLocations() {
  try {
    const xs=await api('/locations');
    $('location').innerHTML='<option value="">Select location</option>'+xs.map(x=>`<option value="${x.id}">${x.name}</option>`).join('')
  }
  catch(e) {
    $('complaintMsg').textContent=e.message
  }
}
async function createComplaint(e) {
  e.preventDefault();
  const m=document.getElementById('complaintMsg');
  try {
    const d=await api('/complaints', {
      method:'POST',body:JSON.stringify( {
        description:$('desc').value,locationId:Number($('location').value),imageUrl:$('imageUrl').value||null
      }
      )
    }
    );
    m.textContent=`Complaint #${d.id} submitted. AI classified it as ${d.aiCategory} with ${d.aiPriority} priority.`;
    $('desc').value='';
    $('imageUrl').value='';
    setTimeout(()=>show('dashboard'),1200)
  }
  catch(x) {
    m.textContent=x.message
  }
}
async function loadStudent() {
  if(!state.token) {
    show('login');
    return
  }
  $('welcome').textContent='Welcome, '+state.user.name;
  try {
    const d=await api('/complaints?page=0&size=20');
    const items=d.content||[];
    const counts= {
      total:d.totalElements,pending:items.filter(x=>x.status==='PENDING').length,progress:items.filter(x=>x.status==='IN_PROGRESS').length,resolved:items.filter(x=>x.status==='RESOLVED').length
    }
    ;
    $('stats').innerHTML=[['Total',counts.total],['Pending',counts.pending],['In progress',counts.progress],['Resolved',counts.resolved]].map(x=>`<article class="stat"><h3>${x[1]}</h3><p>${x[0]}</p></article>`).join('');
    $('complaints').innerHTML=items.length?items.map(c=>`<button class="row complaint-row" onclick="openComplaint(${c.id})"><div><b>#ER-${String(c.id).padStart(5,'0')}</b><div class="muted">${esc(c.description)}</div></div><div><span class="tag">${c.aiCategory||'OTHER'}</span> <span class="tag">${c.status}</span></div></button>`).join(''):'<p class="muted">No complaints yet.</p>'
  }
  catch(e) {
    $('complaints').innerHTML='<p>'+e.message+'</p>'
  }
}
async function openComplaint(id) {
  try {
    const c=await api('/complaints/'+id);
    $('detailContent').innerHTML=`<div class="detail-grid"><div><p class="eyebrow">COMPLAINT</p><h2>#ER-${String(c.id).padStart(5,'0')}</h2><p>${esc(c.description)}</p><p class="muted"><b>Location:</b> ${esc(c.location)}</p></div><div class="panel"><h3>AI Analysis</h3><p><b>Category:</b> ${c.aiCategory}</p><p><b>Priority:</b> ${c.aiPriority}</p><p><b>Summary:</b> ${esc(c.aiSummary)}</p><p><b>Suggested action:</b> ${esc(c.aiRecommendation)}</p><p><b>Status:</b> ${c.status}</p></div></div><div class="panel"><h3>History</h3>${(c.history||[]).map(h=>`<p><b>$ {
      h.newStatus
    }
    </b> — $ {
      esc(h.note||'Status updated')
    }
    <span class="muted">by $ {
      esc(h.changedBy||'System')
    }
    </span></p>`).join('')||'<p class="muted">No history.</p>'}</div>`;
    show('complaintDetail')
  }
  catch(e) {
    alert(e.message)
  }
}
async function loadAdmin() {
  if(!state.token||state.user?.role!=='ADMIN') {
    show('login');
    return
  }
  try {
    const s=await api('/admin/dashboard');
    $('adminStats').innerHTML=[['Total',s.total],['Pending',s.pending],['In progress',s.inProgress],['Resolved',s.resolved],['High priority',s.highPriority]].map(x=>`<article class="stat"><h3>${x[1]}</h3><p>${x[0]}</p></article>`).join('');
    const a=await api('/admin/analytics');
    $('analytics').innerHTML=Object.entries(a.categoryDistribution).map(([k,v])=>`<div class="row"><span>${k.replaceAll('_',' ')}</span><b>${v}</b></div>`).join('');
    const d=await api('/admin/complaints?page=0&size=30');
    $('adminComplaints').innerHTML=d.content.map(c=>`<div class="row"><div><button class="linklike" onclick="openAdminComplaint(${c.id})"><b>#ER-${String(c.id).padStart(5,'0')}</b><div>${esc(c.description)}</div><small class="muted">${c.location}</small></button></div><div><span class="tag">${c.aiCategory}</span><select id="st-${c.id}" style="width:auto;padding:6px"><option ${c.status==='PENDING'?'selected':''}>PENDING</option><option ${c.status==='REVIEWED'?'selected':''}>REVIEWED</option><option ${c.status==='ASSIGNED'?'selected':''}>ASSIGNED</option><option ${c.status==='IN_PROGRESS'?'selected':''}>IN_PROGRESS</option><option ${c.status==='RESOLVED'?'selected':''}>RESOLVED</option></select><button class="secondary" onclick="updateAdmin(${c.id})">Save</button></div></div>`).join('')||'<p class="muted">No complaints.</p>'
  }
  catch(e) {
    $('adminComplaints').innerHTML='<p>'+e.message+'</p>'
  }
}
async function openAdminComplaint(id) {
  try {
    const c=await api('/admin/complaints/'+id);
    alert(`Complaint #ER-${String(c.id).padStart(5,'0')}\n\nAI category: ${c.aiCategory}\nAI priority: ${c.aiPriority}\n\nSummary: ${c.aiSummary}\n\nSuggested action: ${c.aiRecommendation}\n\nStatus: ${c.status}`)
  }
  catch(e) {
    alert(e.message)
  }
}
async function generateInsights() {
  const el=document.getElementById('insights');
  el.innerHTML='<p class="muted">Generating...</p>';
  try {
    const d=await api('/admin/insights', {
      method:'POST'
    }
    );
    el.innerHTML=d.insights.map(i=>`<div class="insight"><h4>${esc(i.title)}</h4><p>${esc(i.description)}</p><b>Suggested action</b><p>${esc(i.recommendation)}</p></div>`).join('')
  }
  catch(e) {
    el.innerHTML='<p>'+e.message+'</p>'
  }
}
async function updateAdmin(id) {
  try {
    await api('/admin/complaints/'+id, {
      method:'PUT',body:JSON.stringify( {
        status:document.getElementById('st-'+id).value
      }
      )
    }
    );
    loadAdmin()
  }
  catch(e) {
    alert(e.message)
  }
}
function openComplaintForm() {
  show('newComplaint')
}
function esc(s) {
  return String(s??'').replace(/[&<>"']/g,c=>( {
    '&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#039;'
  }
  [c]))
}
if(state.token&&state.user)show(state.user.role==='ADMIN'?'admin':'dashboard');
else show('home');
