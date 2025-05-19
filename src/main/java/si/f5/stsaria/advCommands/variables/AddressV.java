package si.f5.stsaria.advCommands.variables;

import java.net.InetSocketAddress;
import java.util.Objects;

public class AddressV extends Variables{
    public AddressV(InetSocketAddress address){
        try {
            this.set("hostname", Objects.requireNonNull(address).getHostName());
            this.set("port", String.valueOf(address.getPort()));
        } catch (Exception ignore) {
            this.set("hostname", "0.0.0.0");
            this.set("port", "65536");
        }
    }
}