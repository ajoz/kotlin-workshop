package io.github.ajoz.workshop.intro;

public final class JLang {
    private final String name;
    private final Integer year;
    private final String developer;

    public JLang(final String name, final Integer year, final String developer) {
        this.name = name;
        this.year = year;
        this.developer = developer;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getDeveloper() {
        return developer;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final JLang jLang = (JLang) o;

        if (!name.equals(jLang.name)) return false;
        if (!year.equals(jLang.year)) return false;

        return developer.equals(jLang.developer);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + developer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "JLang{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", developer='" + developer + '\'' +
                '}';
    }
}
