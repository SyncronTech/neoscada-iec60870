package org.eclipse.neoscada.protocol.iec60870.asdu.message;

import io.netty.buffer.ByteBuf;

import org.eclipse.neoscada.protocol.iec60870.ProtocolOptions;
import org.eclipse.neoscada.protocol.iec60870.asdu.ASDUHeader;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.ASDU;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.Cause;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.CommandValue;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationObjectAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationStructure;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.TypeHelper;

@ASDU ( id = 62, name = "C_SE_TB_1", informationStructure = InformationStructure.SINGLE )
public class SetPointCommandScaledValueTime extends AbstractSetPointCommandScaledValue implements ValueCommandMessage
{
    public SetPointCommandScaledValueTime ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final CommandValue<Short> value, final byte type, final boolean execute )
    {
        super ( header, informationObjectAddress, value, true, type, execute );
    }

    public SetPointCommandScaledValueTime ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final CommandValue<Short> value )
    {
        this ( header, informationObjectAddress, value, (byte)0, true );
    }

    @Override
    public SetPointCommandScaledValueTime mirror ( final Cause cause, final boolean positive )
    {
        return new SetPointCommandScaledValueTime ( this.header.clone ( cause, positive ), this.informationObjectAddress, this.getValue (), this.getType (), this.isExecute () );
    }

    public static SetPointCommandScaledValueTime parse ( final ProtocolOptions options, final byte length, final ASDUHeader header, final ByteBuf data )
    {
        final InformationObjectAddress address = InformationObjectAddress.parse ( options, data );

        final short value = data.readShortLE ();

        final byte b = data.readByte ();

        final byte type = (byte) ( b & 0b011111111 );
        final boolean execute = ! ( ( b & 0b100000000 ) > 0 );

        final long timestamp = TypeHelper.parseTimestamp ( options, data );

        return new SetPointCommandScaledValueTime ( header, address, new CommandValue<Short> ( value, timestamp ), type, execute );
    }
}
