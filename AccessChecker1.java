class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

class AccessChecker1 {
    static String classifyAccess(String fieldModifier,String accessorContext){
        if(fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS")?"ALLOWED":"DENIED";
        if(fieldModifier.equals("default"))
            return accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";
        if(fieldModifier.equals("protected"))
            return accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE")||accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")?"ALLOWED":"DENIED";
        return "ALLOWED";
    }

    static String summarizeByModifier(String[][] attempts){
        String[] modifiers={"private","default","protected","public"};
        int[] allowed=new int[4];
        int[] denied=new int[4];

        for(String[] a:attempts){
            int i=0;
            if(a[0].equals("default")) i=1;
            else if(a[0].equals("protected")) i=2;
            else if(a[0].equals("public")) i=3;

            if(classifyAccess(a[0],a[1]).equals("ALLOWED"))
                allowed[i]++;
            else
                denied[i]++;
        }

        return "private: "+allowed[0]+" allowed / "+denied[0]+" denied | default: "+allowed[1]+" allowed / "+denied[1]+" denied | protected: "+allowed[2]+" allowed / "+denied[2]+" denied | public: "+allowed[3]+" allowed / "+denied[3]+" denied";
    }

    public static void main(String[] args){
        String[][] attempts={
            {"private","SAME_CLASS"},
            {"private","SAME_PACKAGE"},
            {"default","SAME_PACKAGE"},
            {"default","DIFFERENT_PACKAGE"},
            {"protected","SAME_PACKAGE"},
            {"protected","SAME_CLASS"},
            {"public","DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(attempts));
    }
}