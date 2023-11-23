package co.gov.educacionbogota.evaluaciondocenteapi.application.in;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.math.BigDecimal;
import java.time.*;

public class Curso {

    private static String  frase = "Primera Frase";
    public static void main(String args[]){
        String datotamanio = "hoy2";
        String dato = "hoy tenemos que impartir una capacitación, hoy tenemos que impartir una capacitación";
        String dato2 = "hoy tenemos que impartir una capacitación, hoy tenemos que impartir una capacitación";
        String datoSub= dato.substring(1,3);
        System.out.println("Dato : " + dato);
        System.out.println("Dato Substring : " + datoSub);

        System.out.println("Equals : " + dato2.equals(dato));
        System.out.println("dato : " + dato);
        System.out.println("datoSub : " + datoSub);
        datoSub=dato;
        System.out.println("Equals2 : " + datoSub.equals(dato));
        System.out.println("dato indexof : " + dato.indexOf('a',35));
        System.out.println("dato Last indexof : " + dato.lastIndexOf('a'));
        System.out.println("dato chartAt : " + dato.charAt(34));
        System.out.println("datotamanio  : " + datotamanio.length());
        String cadena[] = dato.split(" ");
        System.out.println("dato split  : " + cadena.length);
        System.out.println("cadenas split  : " + cadena[4]);

        StringBuilder sb = new StringBuilder();

        System.out.println("StringBuilder capacity1 : " + sb.capacity());
        //sb. append("te");
        //sb.append("s");
        sb.append(dato);
        System.out.println("StringBuilder tostring  : " + sb.toString());
        System.out.println("StringBuilder capacity 2 : " + sb.capacity());
        System.out.println("String  substring : " + dato.substring(1,3));
        System.out.println("StringBuilder substring : " + sb.substring(1,3));

        System.out.println("dato indexof : " + dato.indexOf('a',35));
        System.out.println("StringBuilder indexof : " + sb.indexOf("a",35));
        System.out.println("dato Lastindexof : " + dato.lastIndexOf('a'));
        System.out.println("StringBuilder Lastindexof : " + sb.lastIndexOf("a"));


        //Wrappers
        int a = 4;
        double b = 40.2435;
        char c = 'b';

        String c1 = "Hola, que tal!!!";
        String c2 = new String("Me llamo Jacinto");


        byte  bytees= 'a';
        short shortes= 'a';
        int intero= 1;
        long longed= 'a';
        float floates= 'a';
        double dos= 'a';
        char charar = 'a';
        boolean booleano= true;

        Byte bb = Byte.MAX_VALUE;
        System.out.println("byte max  : " +  Byte.MAX_VALUE);
        System.out.println("byte min  : " +  Byte.MIN_VALUE);
        System.out.println("compare static  : " + Integer.compare(intero,charar));

        LocalDate someDay =  LocalDate.of(2022, Month.SEPTEMBER, 13);
        System.out.println("someDay   : "+someDay);
        LocalTime someTime =  LocalTime.of(10,6);
        LocalTime someTime2 =  LocalTime.of(11,6);
        System.out.println("someTime   : "+someTime);
        System.out.println("someTime.plus   : "+someTime.plusHours(1));
        LocalDateTime localDt = LocalDateTime.of(2020,Month.JUNE,6, 10,11,59,999999999);
        System.out.println("locaDT   : "+localDt);
        System.out.println("someTime isAfter   : "+someTime.isAfter(someTime2) + " -  isBefore : " + someTime.isBefore(someTime2));
        System.out.println("someDay with atTime   : "+someDay.atTime(someTime));
        System.out.println("someDT to LocalDate   : "+localDt.toLocalDate());
        System.out.println("someDT to LocalTime   : "+localDt.toLocalTime());
        LocalDateTime localDT2 = LocalDateTime.now();
        System.out.println("localDT with   : "+localDt.withMinute(14) );
        System.out.println("localDT plus   : "+localDt. plusMinutes(16) );
        System.out.println("localDT GetMinutes   : "+localDt. getMinute() );
        Instant timstamp = Instant.now();
        System.out.println("localDT timestamp   : "+timstamp );
        int periodDay =3;
        System.out.println("periodDay ofDayy   : "+periodDay );



        Curso obj1 = new Curso();
        Curso obj2 = new Curso();
        System.out.println("Frase Obj1 antes   : "+obj1.frase );
        obj2.frase= "Segunda Frase";


        System.out.println("Frase Obj1   : "+obj1.frase );
        System.out.println("Frase Obj2   : "+obj2.frase );
        System.out.println("Frase Obj2 static  : "+Curso.frase );
        System.out.println("sumar  static  : "+sumar(1,1) );
        System.out.println("Period  ofDays  : "+defaultExpiryPeriod );
        Curso.setDefaultExpiryPeriod(3);






    }

    private static Period defaultExpiryPeriod = Period.ofDays(3);
    private String name;
    private BigDecimal price;
    private LocalDate bestBefore;
    public static void setDefaultExpiryPeriod(int days){
        defaultExpiryPeriod = Period.ofDays(days);
        //String name = this.name;
        String name =new Curso().name;

        System.out.println("name  static  : "+name );
    }


    public static int sumar (int a, int b){
        return a+b;
    }
}
