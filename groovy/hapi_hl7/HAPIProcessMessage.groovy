import ca.uhn.hl7v2.parser.Parser
import ca.uhn.hl7v2.model.Message
import ca.uhn.hl7v2.HapiContext
import ca.uhn.hl7v2.DefaultHapiContext

String msg = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01|12345|P|2.5\r" +
            "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414\r"+
            "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"+
            "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D\r"+
            "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"+
            "AL1||SEV|001^POLLEN\r"+
            "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"+
            "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";
 
HapiContext context = new DefaultHapiContext()           
Parser p = context.getPipeParser();
Message adt = p.parse(msg);

println adt.getClass().getSimpleName() // ADT_A01

println adt.getMSH().getMsh7_DateTimeOfMessage() // TS con el valor 199904140038

println adt.getPID().getPatientName(0) // XPN con el valor SMITH^JOHN^M
println adt.getPID().getPatientName(0).getXpn1_FamilyName() // FN con el valor SMITH
println adt.getPID().getPatientName(0).getXpn1_FamilyName().getFn1_Surname() // ST con el valor SMITH
println adt.getPID().getPatientName(0).getXpn1_FamilyName().getFn1_Surname().getValue() // String con el valor SMITH


