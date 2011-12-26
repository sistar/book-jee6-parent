package ws;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by IntelliJ IDEA.
 * User: RSI
 * Date: 22.12.11
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
@WebService
@SOAPBinding
public interface CxfEjbInterface {
    String sayHello(String name);
}
