package redisSample;

public class SampleDto {
    public String value;
    public long creationDateTime;

    @Override
    public String toString() {
        return "SampleDto String format [value=" + value + ", creationDateTime=" + creationDateTime + "]";
    }

}
