package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);

    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("id", hasSize(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002() {
        response.body("find { it.id == 38788 }.title", equalTo("Aestivus dicta eum utique vae avarus tristis stipes et."));
    }

    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("user_id", hasItem(2223279));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(38788, 38783, 38781 ));
    }
    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
//    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
//    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
//    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
//    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
//    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
//    adflicto. Assentator umquam pel."”
    @Test
    public void test005() {
        response.body("find { it.user_id == 2223226 }.body", equalTo("Sed nobis administratio. Ex advenio porro. Sopor delego articulus. Ustulo valde totam. Est cibo harum. Vesper angustus adhaero. Cupressus denuo amoveo. Sit aut tactus. Cum contabesco adnuo. Aut claro talis. Decretum tandem sui. Numquam sonitus in. Praesentium acies repellat. Impedit spoliatio decretum. Tempora sufficio tergo. Sodalitas voco vindico. Laboriosam atrocitas deficio. Tabula vix thema. Aegrotatio crustulum tabula."));
    }





}
