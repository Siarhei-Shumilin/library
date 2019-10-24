package by.javatr.library.util;

import org.junit.Assert;
import org.junit.Test;


public class BookValidatorTest {

    @Test
    public void testValidateTitleShouldReturnTrue() {
        String title = "Мастер и маргарита";
        boolean valid = BookValidator.validate(title, Constants.GENERAL_PATTERN);
        Assert.assertTrue(valid);
    }

    @Test
    public void testValidateTitleShouldReturnFalseWhenStringIsEmpty() {
        String title = "";
        boolean valid = BookValidator.validate(title, Constants.GENERAL_PATTERN);
        Assert.assertFalse(valid);
    }

    @Test
    public void testValidateDescriptionShouldReturnTrue() {
        String description = "Действие романа начинается в один из майских дней, когда два московских литератора председатель правления МАССОЛИТа Михаил Александрович Берлиоз и поэт Иван Бездомный во время прогулки на Патриарших прудах встречают незнакомца, похожего на иностранца. Он включается в разговор об Иисусе Христе, рассказывает о своём пребывании на балконе прокуратора Иудеи Понтия Пилата и предрекает, что Берлиозу отрежет голову русская женщина, комсомолка.";
        boolean valid = BookValidator.validate(description, Constants.DESCRIPTION_PATTERN);
        Assert.assertTrue(valid);
    }

    @Test
    public void testValidateNumberOfInstancesShouldReturnTrue() {
        String numberOfInstances = "5";
        boolean valid = BookValidator.validate(numberOfInstances, Constants.NUMBER_OF_INSTANCES_PATTERN);
        Assert.assertTrue(valid);
    }
    @Test
    public void testValidateNumberOfInstancesShouldReturnFalse() {
        String numberOfInstances = "sdafasf";
        boolean valid = BookValidator.validate(numberOfInstances, Constants.NUMBER_OF_INSTANCES_PATTERN);
        Assert.assertFalse(valid);
    }
}