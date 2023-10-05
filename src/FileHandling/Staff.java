package FileHandling;

public class Staff {
    private Name name;
    private String id;

    public Staff(){
        this.name = new Name();
    }

    public String getID(){ return this.id;}

    @Override
    public String toString(){
        return getID();
    }
}
