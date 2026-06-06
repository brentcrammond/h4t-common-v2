package nz.h4t.common.startup;

import io.quarkus.runtime.configuration.ConfigUtils;
import io.smallrye.config.SmallRyeConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Paths;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Function;

public abstract class StartupReporter {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger("Startup");

    protected String applicationName;
    protected List<StartupItem> startupItems = new ArrayList<>();

    public void applicationName(String applicationNameProperty) {
        var config = org.eclipse.microprofile.config.ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
        applicationName = config.getOptionalValue(applicationNameProperty, String.class).orElse("Unknown");
    }

    /**
     * Adds default sections to the startup report based on the provided section names.
     * Each section corresponds to a predefined set of properties that are included
     * in the startup report.
     *
     * @param sections an array of section names to be added to the report. If a section
     *                 name matches one of the predefined cases (e.g., "Feature Flags",
     *                 "Build", "GIT"), its corresponding properties are added. Passing
     *                 null or an empty array will result in no sections being added.
     *                 <p>
     *                 <p>
     *                 Example:
     *                 <p>
     *                 addDefaultSections("Feature Flags", "Build", "GIT", "Java", "Run Environment", "Web Server", "Memory", "Database", "Email", "Misc");
     *                 </p>
     */
    public void addDefaultSections(String... sections) {
        if (sections != null) {
            for (var section : sections) {
                switch (section) {
                    case "Feature Flags" -> add("Feature Flags", "Profiles", StartupSpecialProperties.Profiles);
                    case "Build" -> {
                        add("Build", "Version", StartupSpecialProperties.BuildVersion);
                        add("Build", "Timestamp", StartupSpecialProperties.BuildTimestamp);
                    }
                    case "GIT" -> {
                        add("GIT", "Commit Id", StartupSpecialProperties.GITCommitId);
                        add("GIT", "Commit Abbrev Id", StartupSpecialProperties.GITCommitIdAbbrev);
                        add("GIT", "Build Time", StartupSpecialProperties.GITBuildTime);
                    }
                    case "Java" -> {
                        add("Java", "Vendor", StartupSpecialProperties.JavaVendor);
                        add("Java", "Version", StartupSpecialProperties.JavaVersion);
                        add("Java", "Specification Version", StartupSpecialProperties.JavaSpecVersion);
                    }
                    case "Run Environment" -> add("Run Environment", "Current Working Directory", StartupSpecialProperties.CWD);
                    case "Web Server" -> {
                        add("Web Server", "http Port", "quarkus.http.port", Integer.class);
                        add("Web Server", "http Root Path", "quarkus.http.root-path");
                    }
                    case "Memory" -> {
                        add("Memory", "Free Memory", StartupSpecialProperties.FreeMemory);
                        add("Memory", "Allocated Memory", StartupSpecialProperties.AllocatedMemory);
                        add("Memory", "Max Memory", StartupSpecialProperties.MaxMemory);
                        add("Memory", "Total Free Memory", StartupSpecialProperties.TotalFreeMemory);
                    }
                    case "Database" -> {
                        add("Database", "DB Kind", "quarkus.datasource.db-kind");
                        add("Database", "DB URL", "quarkus.datasource.jdbc.url");
                        add("Database", "DB Username", "quarkus.datasource.username");
                    }
                    case "Email" -> {
                        add("Email", "From", "quarkus.mailer.from");
                        add("Email", "Host", "quarkus.mailer.host");
                        add("Email", "Port", "quarkus.mailer.port");
                        add("Email", "Trust All", "quarkus.mailer.trust-all", Boolean.class);
                        add("Email", "SSL", "quarkus.mailer.ssl", Boolean.class);
                        add("Email", "Keep Alive", "quarkus.mailer.keep-alive", Boolean.class);
                    }
                    case "Timezones" -> {
                        addValue("Timezones", "Default Timezone", TimeZone.getDefault().getID());
                        addValue("Timezones", "Java Timezone", System.getProperty("user.timezone"));
                        add("Timezones", "Hibernate Timezone", "quarkus.hibernate-orm.jdbc.timezone");
                    }
                    case "Misc" -> {
                        add("Misc", "Started at", StartupSpecialProperties.StartedAt);
                        add("Misc", "Copyright", StartupSpecialProperties.Copyright);
                    }
                }
            }
        }
    }

    public void add(String section) {
        startupItems.add(new StartupItem(section, "", StartupSpecialProperties.BlankLine));
    }

    public void add(String section, String name, String propertyName, Class propertyClass) {
        startupItems.add(new StartupItem(section, name, propertyName, null, propertyClass, null));
    }

    public void add(String section, String name, String propertyName, String propertyUnits, Class propertyClass, Function<String, String> convert) {
        startupItems.add(new StartupItem(section, name, propertyName, propertyUnits, propertyClass, convert));
    }

    public void add(String section, String name, String propertyName, String propertyUnits, Class propertyClass) {
        startupItems.add(new StartupItem(section, name, propertyName, propertyUnits, propertyClass, null));
    }

    public void add(String section, String name, String propertyName, Function<String, String> convert) {
        startupItems.add(new StartupItem(section, name, propertyName, null, String.class, convert));
    }

    public void add(String section, String name, String propertyName, String propertyUnits) {
        startupItems.add(new StartupItem(section, name, propertyName, propertyUnits, String.class, null));
    }

    public void add(String section, String name, String propertyName) {
        startupItems.add(new StartupItem(section, name, propertyName, null, String.class, null));
    }

    public void add(String section, String name, StartupSpecialProperties specialProperty) {
        startupItems.add(new StartupItem(section, name, specialProperty));
    }

    public void addValue(String section, String name, String value) {
        startupItems.add(new StartupItem(section, name, value));
    }

    public void generateReport() {
        var profiles = String.join(", ", ConfigUtils.getProfiles());
        var runtime = Runtime.getRuntime();
        var javaVendor = System.getProperty("java.vendor");
        var javaVersion = System.getProperty("java.version");
        var javaSpecVersion = System.getProperty("java.specification.version");
        var currentRelativePath = Paths.get("");
        var cwd = currentRelativePath.toAbsolutePath().toString();
        var startedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE d-MMM-yyyy HH:mm:ss"));

        var config = org.eclipse.microprofile.config.ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);

        if (applicationName == null) {
            applicationName("quarkus.application.name");
        }

        var sb = new StringBuilder();
        append(applicationName, sb);
        append(StringUtils.repeat('-', applicationName.length()), sb);

        String lastSection = null;
        for (var itm : startupItems) {
            if (lastSection == null) {
                append(itm.getSection(), sb);
            } else if (!itm.getSection().equals(lastSection)) {
                append(sb);
                append(itm.getSection(), sb);
            }

            switch (itm.getSpecialProperty()) {
                case ConfigProperty ->
                        extractProperty(config, itm.getName(), itm.getPropertyName(), itm.getPropertyUnits(), itm.getPropertyClass(), itm.getConvert(), sb);
                case CustomValue -> append(itm.getName(), itm.getCustomValue(), sb);
                case CWD -> append(itm.getName(), cwd, sb);
                case StartedAt -> append(itm.getName(), startedAt, sb);
                case Profiles -> append(itm.getName(), profiles, sb);
                case JavaVendor -> append(itm.getName(), javaVendor, sb);
                case JavaVersion -> append(itm.getName(), javaVersion, sb);
                case JavaSpecVersion -> append(itm.getName(), javaSpecVersion, sb);
                case MaxMemory -> append(itm.getName(), humanReadableByteCount(runtime.maxMemory()), sb);
                case TotalFreeMemory ->
                        append(itm.getName(), humanReadableByteCount(runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory())), sb);
                case AllocatedMemory -> append(itm.getName(), humanReadableByteCount(runtime.totalMemory()), sb);
                case FreeMemory -> append(itm.getName(), humanReadableByteCount(runtime.freeMemory()), sb);
                case BuildVersion -> extractProperty(config, itm.getName(), "build.version", sb);
                case BuildTimestamp -> extractProperty(config, itm.getName(), "build.timestamp", sb);
                case GITCommitId -> extractProperty(config, itm.getName(), "git.commit.id", sb);
                case GITCommitIdAbbrev -> extractProperty(config, itm.getName(), "git.commit.id.abbrev", sb);
                case GITBuildTime -> extractProperty(config, itm.getName(), "git.build.time", sb);
                case Timezone -> TimeZone.getDefault().getID();
                case JavaTimezone -> System.getProperty("user.timezone");
                case ORMTimezone -> extractProperty(config, itm.getName(), "quarkus.hibernate-orm.jdbc.timezone", sb);
                case Copyright -> extractProperty(config, itm.getName(), "build.copyright", sb);
                case BlankLine -> append(sb);
            }

            lastSection = itm.getSection();
        }

        log.info("");
        log.info(StringUtils.repeat('=', 100));
        Arrays.stream(sb.toString().split("\\r?\\n")).forEach(log::info);
        log.info(StringUtils.repeat('=', 100));
        log.info("");
    }

    private void extractProperty(SmallRyeConfig config, String name, String propertyName, StringBuilder sb) {
        extractProperty(config, name, propertyName, null, String.class, null, sb);
    }

    private void extractProperty(SmallRyeConfig config, String name, String propertyName, String propertyUnits, Class propertyClass, Function<String, String> convert, StringBuilder sb) {
        var sVal = extractProperty(config, propertyName, propertyUnits, propertyClass);
        if (sVal != null) {
            if (convert != null) {
                sVal = convert.apply(sVal);
            }
            append(name, sVal, sb);
        }
    }

    private String extractProperty(SmallRyeConfig config, String propertyName, String propertyUnits, Class propertyClass) {
        var propOpt = config.getOptionalValue(propertyName, propertyClass);
        if (propOpt.isPresent()) {
            String sVal = "";
            Object val = propOpt.get();
            if (val instanceof String) {
                sVal = (String) val;
            } else if (val instanceof LocalDate) {
                sVal = ((LocalDate) val).format(DateTimeFormatter.ofPattern("EEE d-MMM-yyyy"));
            } else if (val instanceof LocalDateTime) {
                sVal = ((LocalDateTime) val).format(DateTimeFormatter.ofPattern("EEE d-MMM-yyyy HH:mm:ss"));
            } else if (val instanceof LocalTime) {
                sVal = ((LocalTime) val).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            } else if (val instanceof File) {
                sVal = ((File) val).getAbsolutePath();
            } else {
                sVal = String.valueOf(val);
            }
            if (StringUtils.isNotBlank(propertyUnits)) {
                sVal = sVal + " " + propertyUnits;
            }
            return sVal;
        }
        return null;
    }

    //
    // Internal Methods...
    //

    private void append(String title, String val, StringBuilder sb) {
        sb.append("  " + title);
        sb.append(':');
        sb.append(StringUtils.repeat('_', 32 - title.length()));
        sb.append(' ');
        sb.append(val);
        sb.append('\n');
    }

    private void append(String val, StringBuilder sb) {
        sb.append(val);
        sb.append('\n');
    }

    private void append(StringBuilder sb) {
        sb.append('\n');
    }

    private String humanReadableByteCount(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + "B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f%cB", value / 1024.0, ci.current());
    }
}
