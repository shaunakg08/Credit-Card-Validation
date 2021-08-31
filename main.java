import java.util.Scanner;

class Final
{   
    private static String cnn,chkd,accno;
    private static int a,sum;
    private static Scanner sc;

    private static void onlynum()
    {   cnn=cnn.replace(" ","");
        String t = "";
        for(int i = 0 ; i < cnn.length(); i++) {
            char c = cnn.charAt(i);
            if (c>='0' && c<='9')
                t += c;
        }
        cnn=t;

    }

    private static String mii()
    {   char c=cnn.charAt(0);
        String m="";
        switch(c)
        {
            case '1':
            case '2':
            m="Airlines";
            break;

            case '3':
            m="Travel";
            break;

            case '4':
            case '5':
            m="Banking and Financial";
            break;

            case '6':
            m="Merchandising and Banking / Financial";
            break;
            case '7':
            m = "Petroleum";
            break;

            case '8':
            m="Healthcare,Telecommunications";
            break;

            case '9':
            case '0':
            m="Unknown";
            break;
        }

        return m;
    }

    private static String iin()
    {   String in=cnn.substring(0,5),info="";
        if(in.startsWith("34")==true || in.startsWith("37")==true)
            info="Amex";
        else if(in.startsWith("4"))
            info="Visa";
        else if(in.startsWith("51") || in.startsWith("52") || in.startsWith("53") ||in.startsWith("54") || in.startsWith("55"))
            info="MasterCard";
        else if(in.startsWith("6011") || in.startsWith("644") || in.startsWith("65"))
            info="Discover";
        else
            info="Unknown";

        return info;
    }

    private static int mod10algosum()
    {   String ccn1 = String.valueOf(cnn);
        int count = 0,d,n;
        for(int i = ccn1.length() - 1; i >= 0; i--){
            n = Integer.parseInt(String.valueOf(ccn1.charAt(i)));
            if(count % 2 == 1){
                d = n * 2;
                if(d > 9)
                    d -= 9;
                sum += d;
            }else
                sum += n;
            count++;
        }
        
      
        return sum;
    }

    private static int fchkd()
    {        int x = (((sum / 10) + 1 ) * 10 - (sum - Integer.parseInt(chkd)));
        if(x > 9)
            x = ((sum / 10) * 10 - (sum -  Integer.parseInt(chkd)));

        return x;
    }

    private static void inputmassign()
    {   
         sc= new Scanner(System.in);
        System.out.println("Enter Credit Card Number: ");
        cnn=sc.nextLine();
        onlynum();

        a=cnn.length();
        chkd=String.valueOf(cnn.charAt(cnn.length() - 1));
        accno =cnn.substring(6, cnn.length() - 1);

    }

    private static void display()
    {  if(a>=12 && a<=19) 
        {   if(mod10algosum()%10==0)
            {  System.out.println(cnn+" is a Valid Credit Card Number");
               System.out.println("Major Industry Identifier (MII): "+mii());
               System.out.println("Issuer Identification Number (IIN): "+iin());
               System.out.println("Account Number: "+accno);
               System.out.println("Check Digit : "+chkd);
            }
            else
           {  
               System.out.println("The Card is Invalid");
               System.out.println("The check digit should have been: "+fchkd());
            }
        }
        else
              System.out.println("The Credit Card is Invalid");
         
        
    }

    public static void main(String args[])
    {  
        inputmassign();
        display();

    }

}