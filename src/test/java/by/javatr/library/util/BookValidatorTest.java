package by.javatr.library.util;

import org.junit.Assert;
import org.junit.Test;


public class BookValidatorTest {

    @Test
    public void testValidateShouldReturnTrue() {
        String title = "Мастер и маргарита";
        boolean valid = BookValidator.validate(title);
        Assert.assertTrue(valid);
    }

    @Test
    public void testValidateDescriptionShouldReturnTrue() {
        String description = "Действие романа начинается в один из майских дней, когда два московских литератора председатель правления МАССОЛИТа Михаил Александрович Берлиоз и поэт Иван Бездомный во время прогулки на Патриарших прудах встречают незнакомца, похожего на иностранца. Он включается в разговор об Иисусе Христе, рассказывает о своём пребывании на балконе прокуратора Иудеи Понтия Пилата и предрекает, что Берлиозу отрежет голову русская женщина, комсомолка.";
        boolean valid = BookValidator.validateDescription(description);
        Assert.assertTrue(valid);
    }

    @Test
    public void testValidateNumberOfInstancesShouldReturnTrue() {
        String numberOfInstances = "5";
        boolean valid = BookValidator.validateNumberOfInstances(numberOfInstances);
        Assert.assertTrue(valid);
    }
}