/* 
 ***************************
 INSTRUCTION:
 1.ENTER CITY + COUNTRY CODE
 EXAMPLE:
 MOSCOW RU
 ***************************
 */
import java.util.*;
import java.net.*;
import java.io.*;
import java.text.*;
public class Program
{
    public static boolean tempInF= false;//If you want, change this parameter and the temperature will be fahrenheit
    public static double KELVIN =-273.15;//Don't modify
    static String json="";// don't modify
        static String[] m = {"   ###","   #  ","     #","    # ","   # #","   ## ","      "};    
    static URL url=null;
    static DateFormat ad = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    static DateFormat ad2 = new SimpleDateFormat("HH:mm dd MMM");
    static int offset = 0;
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            String next2 = sc.next();
                    url = new URL("http://api.openweathermap.org/data/2.5/forecast?appid=8a97869b82c61bb532436f25dfcfcc22&q="+next+","+next2);
            
            InputStream is = url.openConnection().getInputStream();
            BufferedReader reader = new BufferedReader( new InputStreamReader( is ));
            String line = null;
            while( ( line = reader.readLine() ) != null )  {
                json+=line;
            }
            reader.close();
            
            
            ArrayList main = parseValue("main");
            ArrayList name = parseValue("name");
            ArrayList country = parseValue("country");
            System.out.println("  City: "+name.get(0)+" | Country: "+country.get(0));
            System.out.println();
            
            for(int i = 0;i<main.size();i++){
                String spa = "";
                Date e = ad.parse(parseValue("dt_txt").get(i).toString());
                for(int s = 0;s<((34-ad2.format(e).length())/2)-1;s++){
                    spa+="=";
                }
                String sp2 ="";
                if(34-ad2.format(e).length()/2-1 % 2 != 0){
                    sp2 = " ";
                }
                System.out.println();
                System.out.println(spa+ " "+ad2.format(e)+sp2+spa+"\n");
                
                parseInt(parseValue("temp\"").get(i).toString(),parseValue("description").get(i).toString());
                if(tempInF){System.out.println("Fahrenheit temperature  *F");}else{
                    System.out.println("Celsium temperature  *C");
                }
                System.out.println(parseValue("description").get(i));
                    String c1 = "Clouds "+parseValue("all").get(i)+"%";
                String c2 = "Wind "+parseValue("speed").get(i)+"m/s";
                String space ="";
                for(int sp = 0;sp<34-(c1.length()+c2.length());sp++){
                    space+=" ";
                }
                System.out.println(c1+space+c2);
                System.out.println("Humidity "+parseValue("humidity").get(i)+"%");
                System.out.println("==================================\n");
                
            }    
            
}

            catch(Exception e){}
            }
            public static ArrayList parseValue(String s){
                
            offset=0;
                ArrayList al = new ArrayList();
            
                while(offset<json.lastIndexOf(s)){
                    if(s.contains("dt_txt")){
                        al.add(json.substring(json.indexOf(s,offset)+s.length()+2,json.indexOf(",",json.indexOf(s,offset))).replace("\"","").replace(":","-").replace("}",""));
                        offset = json.indexOf(s,offset)+1;

                    }else
                    if(json.indexOf(",",json.indexOf(s,offset))<0){
                        al.add(json.substring(json.indexOf(s,offset)+s.length()+1,json.indexOf("}",json.indexOf(s,offset))).replace("\"","").replace(":","").replace("}",""));
                        offset = json.indexOf(s,offset)+1;
                        
                    }else{
                        al.add(json.substring(json.indexOf(s,offset)+s.length()+1,json.indexOf(",",json.indexOf(s,offset))).replace("\"","").replace(":","").replace("}",""));
                offset = json.indexOf(s,offset)+1;}
                
                }
            return al;
            }
    
        static void parseInt(String fg,String nn){
            String f1 = "";
            String f2 = "";
            String f3 = "";
            String f4 = "";
            String f5 = "";
            String v ="";
            if(tempInF){
                v = String.valueOf(Math.round(1.8*(Double.valueOf(fg)-273)+32));
            }else{
                v = String.valueOf(Math.round(Double.valueOf(fg)+KELVIN));
            }
            if(!v.startsWith("-")){v = "+"+v;}
            if(nn.contains("clear sky")){
                v+="a";
            }else if(nn.contains("light intensity shower rain")){
                v+="b";
            }else if(nn.contains("rain")){
                v+="c";
            }else if(nn.contains("clouds")){
                v+="d";
            }
            char[] v2 = v.toCharArray();
            for(char ch:v2){
                if(ch=='a'){
                    f1+="        \\___/";
                    f2+="      __/   \\__";
                    f3+="        \\___/";
                    f4+="        /   \\";
                }    
                if(ch=='b'){
                    f1+="        \\__/  __";
                    f2+="      __/  \\_/  \\";
                    f3+="        \\__/____/";
                    f4+="        /   / / ";
                    f5+="           / / ";
                }    
                if(ch=='c'){
                    f1+="       __";
                    f2+="    __/  \\_";
                    f3+="   /       \\";
                    f4+="   \\_______/";
                    f5+="    / / / /  ";
                }
                if(ch=='d'){
                    f1+="        \\__/  __";
                    f2+="      __/  \\_/  \\";
                    f3+="        \\__/____/";
                    f4+="        /  \\";
                }    
                if(ch =='0'){
                    f1+=m[0];
                    f2+=m[4];
                    f3+=m[4];
                    f4+=m[4];
                    f5+=m[0];
                }
                if(ch =='1'){
                    f1+=m[3];
                    f2+=m[5];
                    f3+=m[3];
                    f4+=m[3];
                    f5+=m[0];
                }
                if(ch =='2'){
                    f1+=m[0];
                    f2+=m[2];
                    f3+=m[0];
                    f4+=m[1];
                    f5+=m[0];
                }
                if(ch =='3'){
                    f1+=m[0];
                    f2+=m[2];
                    f3+=m[0];
                    f4+=m[2];
                    f5+=m[0];
                }
                if(ch =='4'){
                    f1+=m[4];
                    f2+=m[4];
                    f3+=m[0];
                    f4+=m[2];
                    f5+=m[2];
                }
                if(ch =='5'){
                    f1+=m[0];
                    f2+=m[1];
                    f3+=m[0];
                    f4+=m[2];
                    f5+=m[0];
                }
                if(ch =='6'){
                    f1+=m[0];
                    f2+=m[1];
                    f3+=m[0];
                    f4+=m[4];
                    f5+=m[0];
                }
                if(ch =='7'){
                    f1+=m[0];
                    f2+=m[2];
                    f3+=m[2];
                    f4+=m[2];
                    f5+=m[2];
                }
                if(ch =='8'){
                    f1+=m[0];
                    f2+=m[4];
                    f3+=m[0];
                    f4+=m[4];
                    f5+=m[0];
                }
                if(ch =='9'){
                    f1+=m[0];
                    f2+=m[4];
                    f3+=m[0];
                    f4+=m[2];
                    f5+=m[2];
                }
                if(ch =='+'){
                    f1+="   ";
                    f2+=" # ";
                    f3+="###";
                    f4+=" # ";
                    f5+="   ";
                }
                if(ch =='-'){
                    f1+="   ";
                    f2+="   ";
                    f3+="###";
                    f4+="   ";
                    f5+="   ";
                }
            }
            System.out.println(f1+"\n"+f2+"\n"+f3+"\n"+f4+"\n"+f5);

        }
            }
