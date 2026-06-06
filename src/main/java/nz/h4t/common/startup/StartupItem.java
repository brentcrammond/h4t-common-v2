package nz.h4t.common.startup;

import java.util.function.Function;

public class StartupItem {
    private final String section;
    private final String name;
    private String propertyUnits;
    private final StartupSpecialProperties specialProperty;
    private String propertyName;
    private Class propertyClass;
    private String customValue;
    private Function<String, String> convert;

    public StartupItem(String section, String name, String customValue) {
        this.section = section;
        this.name = name;
        this.specialProperty = StartupSpecialProperties.CustomValue;
        this.customValue = customValue;
    }

    public StartupItem(String section, String name, StartupSpecialProperties specialProperty) {
        this.section = section;
        this.name = name;
        this.specialProperty = specialProperty;
    }

    public StartupItem(String section, String name, String propertyName, String propertyUnits, Class propertyClass, Function<String, String> convert) {
        this.section = section;
        this.name = name;
        this.propertyUnits = propertyUnits;
        this.specialProperty = StartupSpecialProperties.ConfigProperty;
        this.propertyName = propertyName;
        this.propertyClass = propertyClass;
        this.convert = convert;
    }

    public String getSection() {
        return section;
    }

    public String getName() {
        return name;
    }

    public String getPropertyUnits() {
        return propertyUnits;
    }

    public StartupSpecialProperties getSpecialProperty() {
        return specialProperty;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class getPropertyClass() {
        return propertyClass;
    }

    public String getCustomValue() {
        return customValue;
    }

    public Function<String, String> getConvert() {
        return convert;
    }
}
