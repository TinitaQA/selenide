package ru.netology.web;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldSendRequestWhenAllWriteFull(){
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        // LocalDate date = LocalDate.now();
        // LocalDate newDate = date.plusDays(3);
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // String meetingDate = newDate.format(dtf);
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Мэй Мелинда");
        $("[data-test-id='phone'] input").setValue("+79211818665");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + meetingDate));
    }
}
