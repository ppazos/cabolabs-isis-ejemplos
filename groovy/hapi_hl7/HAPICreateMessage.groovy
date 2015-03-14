import ca.uhn.hl7v2.DefaultHapiContext
import ca.uhn.hl7v2.HapiContext
import ca.uhn.hl7v2.parser.Parser

// Importante! al importar tener en cuenta la version, aquí es 2.5
import ca.uhn.hl7v2.model.v25.message.ADT_A01
import ca.uhn.hl7v2.model.v25.segment.MSH
import ca.uhn.hl7v2.model.v25.segment.PID

// Crea el mensaje ADT, ver que la versión de HL7 se establece de forma automática
ADT_A01 adt = new ADT_A01()

// Utiliza el inicializador rápido: tipo de mensaje, evento, identificador de procesamiento
// Ref: http://hl7api.sourceforge.net/v25/apidocs/index.html
adt.initQuickstart("ADT", "A01", "P")
          
// Pone datos en el segmento MSG
// Notar que cuando se le pide un segmento al mensaje, o un campo al segmento, HAPI crea el elemento pedido si no existía, entonces nunca sera null.
MSH mshSegment = adt.getMSH()
mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem")
mshSegment.getSequenceNumber().setValue("123")

// Pone datos en el segmento PID
PID pid = adt.getPID()
pid.getPatientName(0).getFamilyName().getSurname().setValue("Pazos")
pid.getPatientName(0).getGivenName().setValue("Pablo")
pid.getPatientIdentifierList(0).getIDNumber().setValue("123456")
  
/*
 * En una situación real, se pondrían datos para otros segmentos.
 */

// Mostrar el mensaje en ER7 y XML
HapiContext context = new DefaultHapiContext();
Parser parser = context.getPipeParser();
String encodedMessage = parser.encode(adt);

println "ER7"

// Normalize de Groovy permite mostrar los <CR> (fin de segmento) que es el enter de Linux, como <CR><LF> que es el enter de Windows.
// Sin esto, se verían todos los segmentos en la misma línea cuando trabajamos en Windows.
println encodedMessage.normalize()


// Ahora se muestra en XML
parser = context.getXMLParser();
encodedMessage = parser.encode(adt);
println "XML Encoded Message:"
println encodedMessage