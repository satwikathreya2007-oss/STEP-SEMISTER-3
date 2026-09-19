class GymMember {
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

    static String signUpBatch(String[] memberIds,int monthlyFee){
        int signedUp=0,rejected=0;
        for(String id:memberIds){
            try{
                new GymMember(id,monthlyFee);
                signedUp++;
            }catch(IllegalArgumentException e){
                rejected++;
            }
        }
        return "Signed Up: "+signedUp+" | Rejected: "+rejected;
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
