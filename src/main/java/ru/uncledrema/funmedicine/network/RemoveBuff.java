package ru.uncledrema.funmedicine.network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import ru.uncledrema.funmedicine.entity.BuffsProvider;
import ru.uncledrema.funmedicine.entity.IBuffs;
import ru.uncledrema.funmedicine.network.AbstractMessage.AbstractClientMessage;

public class RemoveBuff extends AbstractClientMessage<RemoveBuff> {

	private byte buffId;
	
	public RemoveBuff() {}
	
	public RemoveBuff(int buffId) {
		
		this.buffId = (byte) buffId;
	}
	
	@Override
	protected void writeData(PacketBuffer buffer) throws IOException {
		
		buffer.writeByte(this.buffId);
	}

	@Override
	protected void readData(PacketBuffer buffer) throws IOException {
		
		this.buffId = buffer.readByte();
	}

	@Override
	public void performProcess(EntityPlayer player, Side side) {
		
		IBuffs buffs = player.getCapability(BuffsProvider.BUFFS_CAP, null);

		if (buffs.isBuffActive(this.buffId)) {
			
			buffs.removeActiveBuffFromMap(this.buffId);;
		}	
	}
}

