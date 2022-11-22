package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class deliveryCardTest {

        String meetingDay(int day) {
            return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        @Test
        void bookCard() {
            open("http://localhost:9999");

            $("[data-test-id='city'] input").setValue("Санкт-Петербург");
            $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
            $("[data-test-id='date'] input").setValue(meetingDay(10));
            $("[data-test-id='name'] input").setValue("Дарья Фокина");
            $("[data-test-id='phone'] input").setValue("+79999999999");
            $("[data-test-id='agreement']").click();
            $("button.button").click();
            $(".notification__content")
                    .shouldBe(Condition.visible, Duration.ofSeconds(15))
                    .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDay(10)));

        }

    }
