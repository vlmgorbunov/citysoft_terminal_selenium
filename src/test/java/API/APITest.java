package API;

import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static io.restassured.RestAssured.given;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APITest extends Utils {

    Utils utils;

    private final static String devcsApiURL = "https://devcs.avtodoria.ru/terminal/api/rest";
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    String currentDateStartOfDay = utils.currentDateStartOFDayISO8601.toString();

    String currentDateEndOfDay = utils.currentDateEndOFDayISO8601.toString();

    String yesterdayStartOfDay = utils.yesterdayStartOfDayISO8601.toString();

    String yesterdayEndOfDay = utils.yesterdayEndOFDayISO8601.toString();

    String lastWeek = utils.lastWeekISO8601.toString();

    String lastMonth = utils.lastMonthISO8601.toString();

    public String uuid;

    public String processingStatus;

    public String creationDate;

    @Ignore
    @Order(1)
    @Test
    public void postAllLastMonth() throws IOException, InterruptedException {

        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + lastMonth + "\",\n" +
                        "    \"dateTo\" : \"" + currentDate + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/all")
                .prettyPeek()
                .body()
                .jsonPath();
        Assertions.assertEquals(response.get("descriptions.onDevice[0]"), "VladTest", "description.onDevise is equals");
    }

    @Ignore
    @Order(2)
    @Test
    public String postCountCorruptedLastMonth() throws IOException, InterruptedException {

           String response =    given()
                                 .filter(new AllureRestAssured())
                                 .log().all()
                                 .contentType("application/json")
                                 .when()
                                 .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + lastMonth + "\",\n" +
                        "    \"dateTo\" : \"" +  currentDateEndOfDay  + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" :[\"CORRUPTED\"],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/count")
                .then().statusCode(200)
                .body(Matchers.anything())
                   .extract().asString();
              System.out.println(response);
              return response;
    }
    @Order(3)
    @Test
    public String postCountLastWeek() throws IOException, InterruptedException {

        String response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .header("content-type", "application/json")
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + lastWeek + "\",\n" +
                        "    \"dateTo\" : \"" + currentDateEndOfDay + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/count")
                .then().statusCode(200)
                .body(Matchers.anything())
                .extract().asString();
        System.out.println(response);
        return response;

    }

    @Ignore
    @Order(4)
    @Test
    public void postAllLastWeek() throws IOException, InterruptedException {

        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + lastWeek + "\",\n" +
                        "    \"dateTo\" : \"" + currentDate + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/all")
                .prettyPeek()
                .body()
                .jsonPath();


        String videoId = response.get("videoId[0]");
        System.out.println(videoId);
        Assertions.assertNotNull(response.get(videoId));


    }
    @Feature("(API) #77757 [Terminal] Очередь видео, при пустом поле pageSize запрос /all возвращает 500 ошибку со StackTrace")
    @Order(5)
    @Test
    public void postAllYesterday() throws IOException, InterruptedException {

                 given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + yesterdayStartOfDay + "\",\n" +
                        "    \"dateTo\" : \"" + yesterdayEndOfDay + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : ,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                         .post(devcsApiURL + "/preprocessing/video/all")
                         .prettyPeek()
                         .then().statusCode(200);



    }
    @Order(5)
    @Test
    public String postCountYesterday() throws IOException, InterruptedException {

        String response = given()
                .filter(new AllureRestAssured())
                .log().parameters()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + yesterdayStartOfDay + "\",\n" +
                        "    \"dateTo\" : \"" + yesterdayEndOfDay + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/count")
                .then().statusCode(200)
                .body(Matchers.anything())
                .extract().asString();
        return response;
    }

    @Ignore
    @Order(6)
    @Test
    public void postMarkAsCorrupted() throws IOException, InterruptedException {


        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + lastWeek + "\",\n" +
                        "    \"dateTo\" : \"" + currentDate + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/all")
                .prettyPeek()
                .body()
                .jsonPath();

        uuid = response.get("uuid[0]");
        System.out.println(uuid);


        given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .put(devcsApiURL + "/video/markAsCorrupted/" + uuid)
                .prettyPeek()
                .body()
                .jsonPath();

        processingStatus = response.get("processingStatus[0]");
        System.out.println(processingStatus);
        Assertions.assertEquals((processingStatus), "CORRUPTED", "Video is CORRUPTED");


    }

    @Ignore
    @Order(7)
    @Test
    public void checkAllLastWeek() throws IOException, InterruptedException {

        List<VideoData> videoData = given()
                .when()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + lastWeek + "\",\n" +
                        "    \"dateTo\" : \"" + currentDate + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/all")
                .prettyPeek()
                .then().log().all()
                .extract().body().jsonPath().getList("videoId", VideoData.class);
        videoData.forEach(x -> Assertions.assertTrue(x.getFilePath().contains(x.getVideoId().toString())));

    }
    @Ignore
    @Order(8)
    @Test
    public void postSave() throws IOException, InterruptedException {

        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"creationDate\" : \"" + currentDate + "\",\n" +
                        "    \"operatorId\" : 1,\n" +
                        "    \"inputMatrix\" : {\"points\":[{\"x\":50,\"y\":50},{\"x\":100,\"y\":50},{\"y\":100},{\"x\":150,\"y\":100}],\"matrixWidth\":2,\"matrixHeight\":2,\"cellWidth\":3.5,\"cellHeight\":3},\n" +
                        "    \"shift\" : {\"horizontalShift\":3,\"verticalShift\":186,\"multiplicationFactor\":0.6},\n" +
                        "    \"matrix\" : {\"matrix\":[[-3.8500000000000028,-7.970000000000006,488.00000000000017],[0,-19.34000000000002,731.0000000000008],[0,-0.040000000000000036,1]],\"scale\":0.016666666666666666,\"sceneCenter\":{\"x\":200.25,\"y\":483.375}},\n" +
                        "    \"videoId\" :  \"9af53a0c-30ad-476b-b117-ce3de0f49ced \",\n" +
                        "    \"videoFrameTimestamp\" : 0,\n" +
                        "    \"sourceHomographyId\" : 870\n" +
                        "}")
                .post(devcsApiURL + "/homography/save")
                .prettyPeek()
                .body()
                .jsonPath();
    }

    @Ignore
    @Order(2)
    @Test
    public Integer postCountTestContractLastMonth() throws IOException, InterruptedException {

        Integer response = Integer.valueOf(given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[\"1\"],\n" +
                        "    \"dateFrom\" : \"" + lastMonth + "\",\n" +
                        "    \"dateTo\" : \"" +  currentDateEndOfDay  + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : 10,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/count")
                .then().statusCode(200)
                .body(Matchers.anything())
                .extract().asString());
        System.out.println(response);
        return response;
    }

    @Ignore
    @Order(9)
    @Test
    public void postAllCheckPageSizeNegative() throws IOException, InterruptedException {

        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"contractIds\" :[],\n" +
                        "    \"dateFrom\" : \"" + lastMonth + "\",\n" +
                        "    \"dateTo\" : \"" + currentDate + "\",\n" +
                        "    \"deviceIds\" :[],\n" +
                        "    \"operatorId\" : null,\n" +
                        "    \"pageNumber\" : 0,\n" +
                        "    \"pageSize\" : null,\n" +
                        "    \"processingStatuses\" : [],\n" +
                        "    \"sessionId\" : null,\n" +
                        "    \"sortOrder\" : \"DESC\"\n" +
                        "}")
                .post(devcsApiURL + "/preprocessing/video/all")
                .prettyPeek()
                .body()
                .jsonPath();
 //       Assertions.assertEquals(response.get("descriptions.onDevice[0]"), "test", "description.onDevise is equals");
    }
}



