package com.ecoresolve;
import com.ecoresolve.enums.*; import com.ecoresolve.service.AIService; import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class AIServiceTest { @Test void waterComplaintIsClassified(){var r=new AIService().analyze("The tap beside Lab 2 is leaking and wasting water");assertEquals(ComplaintCategory.WATER,r.category());assertEquals(Priority.HIGH,r.priority());} @Test void wasteComplaintIsClassified(){var r=new AIService().analyze("The garbage bin near cafeteria is overflowing");assertEquals(ComplaintCategory.WASTE,r.category());} }
