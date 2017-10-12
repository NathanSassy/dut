import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public final String tests[] = {
            "140824 07:09:30 alice: guest bob",
            "140824 07:09:31 alice: guest grpA:bob",
            "140824 07:09:32 alice: guest grpA:",
            "140824 07:09:33 bob: welcome alice@10.0.0.1:8080",
            "140824 07:09:34 alice: welcome bob@10.0.0.2:8081, chalie@10.0.0.1:8082",
            "140824 07:09:35 alice: send 10.0.0.1:8080-A",
            "140824 07:09:36 alice: answer 10.0.0.1:8082-C",
            "140824 07:09:37 alice: reject 10.0.0.1:8080-A",
            "140824 07:09:38 alice: close",
            "140824 07:09:39 bob: full",
            "140824 07:09:40 bob: done 10.0.0.2:8081-[ACD] 10.0.0.2:8082-[BD] 10.0.0.2:8083-F"
    };

    public static void main(String[] args) {
        System.out.println("cas nominaux : \n");
        System.out.println("analyseAdr(\"10.0.0.1:8080\") = " + analyseAdr("10.0.0.1:8080"));
        System.out.println("analyseAdr(\"10.0.0.1:8080-A\") = " + analyseAdr("10.0.0.1:8080-A"));
        System.out.println("analyseAdr(\"10.0.0.1:8080-A\") = " + analyseAdr("10.0.0.1:8080-A"));
        System.out.println("analyseAdr(\"10.0.0.2:8081-[ACD]\") = " + analyseAdr("10.0.0.2:8081-[ACD]"));
        System.out.println("analyseAdr(\"10.0.0.2:8081-[ACD] 10.0.0.2:8081-[ACD]\") = " + analyseAdr("10.0.0.2:8081-[ACD] 10.0.0.2:8081-[ACD]"));

        System.out.println("\ncas limites: \n");
        System.out.println("analyseAdr(\"10.0.0.1:80801111\") = " + analyseAdr("10.0.0.1:80801111"));
    }

    private static boolean isDate(String date) {
        Pattern p = Pattern.compile("\\d*\\s(\\d{2}):(\\d{2}):(\\d{2})\\s*(.+)\\s*");
        Matcher m = p.matcher(date);
        return m.matches();
    }

    private static boolean analyseAdr(String adr) {
        String regxAdresseIp = "((\\d{1,3}).(\\d{1,3}).(\\d{1,3}).(\\d{1,3}):(\\d{1,5}))";
        String regxAdressePort = "((-\\w)?|(-\\[\\w+\\])?)";
        String regxFinal = "("+ regxAdresseIp + regxAdressePort + "\\s*)*";
        Pattern p = Pattern.compile(regxFinal);
        Matcher m = p.matcher(adr);
        return m.matches();
    }
}
