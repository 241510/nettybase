package netty.attribute;

import io.netty.util.AttributeKey;

/**
 * @author gaoyanwei
 * @date 2018/10/11.
 */
public interface Attributes {

	AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
