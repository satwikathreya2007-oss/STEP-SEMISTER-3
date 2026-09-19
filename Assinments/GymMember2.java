class GymMember2 {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId,int monthlyFee){
        if(memberId==null||memberId.trim().length()<4)
            throw new IllegalArgumentException();
        this.memberId=memberId;
        this.monthlyFee=monthlyFee;
    }

    void attendSession(){
        sessionsAttended++;
    }

    int getSessionsAttended(){
        return sessionsAttended;
    }

    void displayInfo(){
        System.out.println("Standard Member | Sessions: "+sessionsAttended);
    }
}

class PremiumMember extends GymMember {
    String trainerName;

    public PremiumMember(String memberId,int monthlyFee,String trainerName){
        super(memberId,monthlyFee);
        this.trainerName=trainerName;
    }

    @Override
    void displayInfo(){
        System.out.println("Premium Member | Trainer: "+trainerName+" | Sessions: "+sessionsAttended);
    }
}

class EliteMember extends PremiumMember {
    String lockerNumber;

    public EliteMember(String memberId,int monthlyFee,String trainerName,String lockerNumber){
        super(memberId,monthlyFee,trainerName);
        this.lockerNumber=lockerNumber;
    }

    @Override
    void displayInfo(){
        System.out.println("Elite Member | Trainer: "+trainerName+" | Locker: "+lockerNumber+" | Sessions: "+sessionsAttended);
    }
}

class GroupClassMember extends GymMember {
    String className;

    public GroupClassMember(String memberId,int monthlyFee,String className){
        super(memberId,monthlyFee);
        this.className=className;
    }

    @Override
    void displayInfo(){
        System.out.println("Group Class Member | Class: "+className+" | Sessions: "+sessionsAttended);
    }
}

class Main {
    static String classifyGeneration(GymMember member){
        if(member instanceof EliteMember)
            return "Multilevel descendant (3 generations deep)";
        if(member instanceof GroupClassMember)
            return "Hierarchical sibling (independent branch)";
        if(member instanceof PremiumMember)
            return "Premium Member";
        return "Standard Member";
    }

    static int getTotalSessionsAttended(GymMember[] members){
        int total=0;
        for(GymMember m:members)
            total+=m.getSessionsAttended();
        return total;
    }

    public static void main(String[] args){
        PremiumMember p=new PremiumMember("MEM2",2000,"Coach Riya");
        EliteMember e=new EliteMember("MEM3",3000,"Coach Arjun","L12");
        GroupClassMember g=new GroupClassMember("MEM4",1500,"Zumba");

        p.attendSession();
        p.attendSession();
        p.attendSession();
        e.attendSession();
        e.attendSession();
        g.attendSession();
        g.attendSession();
        g.attendSession();
        g.attendSession();

        p.displayInfo();
        e.displayInfo();
        g.displayInfo();

        System.out.println(classifyGeneration(e));
        System.out.println(classifyGeneration(g));
        System.out.println(getTotalSessionsAttended(new GymMember[]{p,e,g}));
    }
}
