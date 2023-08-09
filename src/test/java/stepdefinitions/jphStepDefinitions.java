package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class jphStepDefinitions {
    String endpoint;
    Response response;
    JsonPath responseJP;
    JSONObject request;

    @Given("Kullanici {string} base URL'ini kullanir")
    public void kullanici_base_url_ini_kullanir(String string) {
      endpoint=ConfigReader.getProperty("jPHBaseUrl");
    }

    @Then("Path parametreleri icin {string} kullanir")
    public void path_parametreleri_icin_kullanir(String pathparams) {
        endpoint=endpoint+ "/"+pathparams;   //path parametreleri ekledik


    }

    @Then("jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_get_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
        response=given().when().get(endpoint);


    }

    @Then("jPH respons'da status degerinin {int}")
    public void j_ph_respons_da_status_degerinin(Integer statusCode) {
        Assert.assertEquals(statusCode,(Integer) response.statusCode());  //casting yaptik response'dan gelen cevbin int oldugunu biliyoruz



    }

    @Then("jPH respons'da content type degerinin {string}")
    public void j_ph_respons_da_content_type_degerinin(String contentType) {
        Assert.assertEquals(contentType,response.contentType());

    }

    @Then("jPH GET respons body'sinde {string} degerinin Integer {int}")
    public void j_ph_get_respons_body_sinde_degerinin_integer(String attribute, Integer expectedValue) {

        responseJP=response.jsonPath();
        Assert.assertEquals(expectedValue,(Integer) responseJP.getInt(attribute));  //casting yaptik



    }

    @Then("jPH GET respons body'sinde {string} degerinin String {string}")
    public void j_ph_get_respons_body_sinde_degerinin_string(String attribute, String expectedValue) {

        responseJP=response.jsonPath();
        Assert.assertEquals(expectedValue,responseJP.getString(attribute));

    }

    @Then("POST request icin {string},{string},{int} {int} bilgileri ile request body olusturur")
    public void post_request_icin_bilgileri_ile_request_body_olusturur(String title, String body, Integer userId, Integer id) {

        request=new JSONObject();
        request.put("title","title");
        request.put("body","body");
        request.put("userId",userId);
        request.put("id","id");
    }
    @Then("jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_post_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
        response=given()
                .when().body(request.toString()).contentType(ContentType.JSON)
                .put(endpoint);

    }
    @Then("jPH respons daki {string} header degerinin {string}")
    public void j_ph_respons_daki_header_degerinin(String headerAttribute, String attributeValue) {

        Assert.assertEquals(attributeValue,response.header(headerAttribute));

    }
    @Then("response attribute degerlerinin {string},{string},{int} {int}")
    public void response_attribute_degerlerinin(String title, String body, Integer userId, Integer id) {
        responseJP=response.jsonPath();

        Assert.assertEquals(title,responseJP.getString("title"));
        Assert.assertEquals(body,responseJP.getString("body"));
        Assert.assertEquals(userId,(Integer) responseJP.getInt("userId"));
        Assert.assertEquals(id,(Integer) responseJP.getInt("id"));

    }

}
