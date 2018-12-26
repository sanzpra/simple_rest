package es.sanzpra.dev.onlineshipmenttracker.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.sanzpra.dev.onlineshipmenttracker.OnlineShipmentTrackerApplication;
import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatus;
import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatusCode;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Parcel;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Shipment;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Tracking;
import es.sanzpra.dev.onlineshipmenttracker.persistance.repository.ShipmentRepository;
import es.sanzpra.dev.onlineshipmenttracker.service.BusinessLogicService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>RestWebControllerIntegrationTest</code> class runs integration tests for the
 * controller.
 * It tests the put and post requests.
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = OnlineShipmentTrackerApplication.class)
@AutoConfigureMockMvc
public class RestWebControllerTest {
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private BusinessLogicService service;
    
    @MockBean
    private ShipmentRepository shipmentRepository;
    
    @Test
	public void givenShipment_whenPostShipment_thenReturnJsonArray() throws Exception {
    	// given
    			Parcel parcel1 = new Parcel();
    			parcel1.setId(10001L);
    			parcel1.setShipmentReference("ABCD123456");
    			parcel1.setWeight(1);
    			parcel1.setWidth(10);
    			parcel1.setHeight(10);
    			parcel1.setLenght(10);
    			
    			Parcel parcel2 = new Parcel();
    			parcel2.setId(10002L);
    			parcel2.setShipmentReference("ABCD123456");
    			parcel2.setWeight(2);
    			parcel2.setWidth(20);
    			parcel2.setHeight(20);
    			parcel2.setLenght(20);
    			
    			List<Parcel> parcelsTester = new ArrayList<Parcel>();
    			parcelsTester.add(parcel1);
    			parcelsTester.add(parcel2);
    			
    			Shipment shipmentTester = new Shipment();
    			shipmentTester.setReference("ABCD123456");
    			shipmentTester.setParcels(parcelsTester);
    			
    			when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipmentTester);
    			
    			mvc.perform(post("/api/register")
    					  .content(asJsonString(shipmentTester))
    				      .contentType(MediaType.APPLICATION_JSON))
    					  .andDo(print())
    				      .andExpect(status().isOk());
    				      //.andExpect(jsonPath("$", hasSize(1)))
    				      //.andExpect(jsonPath("$[0].reference", is(shipmentTester.getReference())));
    }
    
	@Test
	public void givenTracking_whenPutTracking_thenReturnJsonArray() throws Exception {
		
		Tracking trackingTester = new Tracking();
	    trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("WAITING_IN_HUB");
		trackingTester.setParcels(2);
		trackingTester.setWeight(null);
		trackingTester.setReference("ABCD123456");
		
		ShipmentStatus shipmentStatusTester = new  ShipmentStatus("ABCD123456",ShipmentStatusCode.INCOMPLETE.toString());
		
	 
		when(service.eventBuilder(any(Tracking.class))).thenReturn(shipmentStatusTester);
	 
		MvcResult result = mvc.perform(put("/api/push")
				  .content(asJsonString(trackingTester))
			      .contentType(MediaType.APPLICATION_JSON))
				  .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.status", is(trackingTester.getStatus())))
		.andExpect(jsonPath("$.parcels", is(trackingTester.getParcels())))
		.andExpect(jsonPath("$.weight", is(trackingTester.getWeight())))
		.andExpect(jsonPath("$.reference", is(trackingTester.getReference()))).andReturn();
			      //.andExpect(jsonPath("$", hasSize(1)))
			      //.andExpect(jsonPath("$[0].reference", is(trackingTester.getReference())));
				 //andExpect(content().json("{'data':[{'useRegEx':'false','hosts':'v2v2v2'}]}"));
		String expected = "{\"id\":1,\"status\":\"WAITING_IN_HUB\",\"parcels\":2,\"weight\":null,\"reference\":\"ABCD123456\"}";


		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		//assertThat(this.json.parseObject(result.getResponse().getContentAsString()).getReference()).isEqualTo("ABCD123456");
		
		
	    	      
	}
    
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  

}
