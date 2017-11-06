import com.yundan.server.handler.arrow.MessageDecodeHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.junit.Test;

import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.util.ReferenceCountUtil.releaseLater;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LineBasedFrameDecoderTest {
    @Test
    public void testDecodeWithStrip() throws Exception {
        EmbeddedChannel ch = new EmbeddedChannel(new MessageDecodeHandler());

        ch.writeInbound(copiedBuffer("hello:test", CharsetUtil.US_ASCII));
//        assertEquals("hello", releaseLater((ByteBuf) ch.readInbound()).toString(CharsetUtil.US_ASCII));
//        assertEquals("test", releaseLater((ByteBuf) ch.readInbound()).toString(CharsetUtil.US_ASCII));
        assertNull(ch.readInbound());
        ch.finish();

        ReferenceCountUtil.release(ch.readInbound());
    }
}
