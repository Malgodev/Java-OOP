package FileHandling;

class Name implements Comparable<Name>{
    private String name, firstName, lastName, middleName;
    private String[] nameSplit;

    public Name(){
        this.name = "";
    }

    public Name(String name){
        this.name = nameNormalize(name);
        this.nameSplit = this.name.split(" ");
        this.firstName = nameSplit[0];
        this.lastName = nameSplit[nameSplit.length - 1];
        this.middleName = "";
        for (int i = 1; i < nameSplit.length - 1; i++){
            this.middleName += nameSplit[i];
        }
    }

   public String nameNormalize(String s){
        s = s.trim().toLowerCase();
        String[] tmp = s.split("\\s+");
        s = "";
        for (int i = 0; i < tmp.length; i++){
            s += String.valueOf(tmp[i].charAt(0)).toUpperCase();
            s += tmp[i].substring(1) + " ";
        }
        return s.substring(0, s.length() - 1);
    }    

    public String getFirstName(){ return this.firstName;}
    public String getLastName(){ return this.lastName;}
    public String getMiddleName(){ return this.middleName;}

    @Override
    public int compareTo(Name another){
        int tmp1 = this.lastName.compareTo(another.getLastName());
        int tmp2 = this.firstName.compareTo(another.getFirstName());
        int tmp3 = this.middleName.compareTo( another.getMiddleName());

        if (tmp1 == 0){
            if (tmp2 == 0){
                return tmp3;
            }else return tmp2;
        }else return tmp1;
    }

    @Override 
    public String toString(){
        return String.format("%s", name);
    }
}