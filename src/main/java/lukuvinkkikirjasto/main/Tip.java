package lukuvinkkikirjasto.main;

public class Tip implements TipI {

    private String header;
    private String creator;
    private String url;
    private String type;
    private String description;
    private String tags;
    private String comment;
    private String courses;

    public Tip(String header, String description, String creator, String url, String type, String tags, String comment,
               String courses) {
        this.header = header;
        this.creator = creator;
        this.url = url;
        this.type = type;
        this.description = description;
        this.tags = tags;
        this.comment = comment;
        this.courses = courses;
    }

    public String getHeader() {
        return this.header;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCreator() {
        return this.creator;
    }

    public String getUrl() {
        return this.url;
    }

    public String getType() {
        return this.type;
    }

    public String getTags() {
        return this.tags;
    }

    public String getComment() {
        return this.comment;
    }

    public String getCourses() {
        return this.courses;
    }

    @Override
    public String toString() {
        return "Header: " + this.header + "\nWriter: " + this.creator + "\nDescription: " +
                this.description + "\nURL: " + this.url + "\nType: " + this.type +
                "\nTags: " + this.tags + "\nComments: " + this.comment + "\nRelated courses: " + this.courses + "\n\n";
    }
    @Override
    public int hashCode() {
        int result = 17;
        return result = 31 * result + header.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tip tip = (Tip) o;
        return header.equals(tip.header) && creator.equals(tip.creator) && url.equals(tip.url) && type.equals(tip.type) && description.equals(tip.description) && tags.equals(tip.tags) && comment.equals(tip.comment) && courses.equals(tip.courses);
    }

}
