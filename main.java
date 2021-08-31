import java.util.Scanner;

class Validate_Credit_Card
{   
    private static String cnn,accno;
    private static int a,sum,chkd;
    private static Scanner sc;

    //function to remove alphabets and spaces
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

    //function to identify Major Industry Identifier(MII)
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

            default :
            m="Unknown";
            break;
        }

        return m;
    }//end of mii()

    //function to identify Issuer Identification Number (IIN)
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

    //function to extract digit and take out the sum using mod10 algorithm
    private static int mod10algosum()
    {   String t = String.valueOf(cnn);
        int count = 0,p,n;
        for(int i = t.length() - 1; i >= 0; i--){
            n = Integer.parseInt(String.valueOf(t.charAt(i)));
            if(count % 2 == 1){
                p = n * 2;
                if(p> 9)
                    p -= 9;
                sum += p;
            }else
                sum += n;
            count++;
        }
        
      
        return sum;
    }
    
    //to find the correct check digit if card number is not valid
    private static int crtchkd()
    {  
        int nr = (((sum / 10) + 1 ) * 10 - (sum - chkd));
        if(nr > 9)
            nr = ((sum / 10) * 10 - (sum - chkd));
        return nr;
    }
  
    //input card number and find length, check digit and account number
    private static void inputmassign()
    {   
        sc = new Scanner(System.in);
        System.out.println("Enter Credit Card Number: ");
        cnn=sc.nextLine();
        onlynum();

        a=cnn.length();
        chkd=Integer.parseInt(String.valueOf(cnn.charAt(cnn.length() - 1)));
        accno =cnn.substring(6, cnn.length() - 1);

    }

    
    //display the output
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
               System.out.println("The check digit should have been: "+crtchkd());
            }
        }
        else
              System.out.println("The Credit Card is Invalid");
         
        
    }

    
                //main function to call other  
    public static void main(String[] args)
    {  
        inputmassign();
        display();

    }

}