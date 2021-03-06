/*
 * Copyright (c) 2017, Andreas Fagschlunger. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.o2xfs.xfs.v3_10.cdm;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.cdm.CdmGuidLights;
import at.o2xfs.xfs.cdm.DevicePosition;
import at.o2xfs.xfs.v3_00.cdm.CdmStatus3;
import at.o2xfs.xfs.win32.XfsBitmaskArray;
import at.o2xfs.xfs.win32.XfsWord;

public class CdmStatus310 extends CdmStatus3 {
	private static final int GUIDLIGHTS_SIZE = 32;

	protected final XfsBitmaskArray<CdmGuidLights> guidLights = new XfsBitmaskArray<>(GUIDLIGHTS_SIZE, CdmGuidLights.class);
	protected final XfsWord<DevicePosition> devicePosition = new XfsWord<>(DevicePosition.class);
	protected final USHORT powerSaveRecoveryTime = new USHORT();

	protected CdmStatus310() {
		add(guidLights);
		add(devicePosition);
		add(powerSaveRecoveryTime);
	}

	public CdmStatus310(Pointer p) {
		this();
		assignBuffer(p);
	}

	public CdmStatus310(CdmStatus310 copy) {
		this();
		allocate();
		set(copy);
	}

	protected void set(CdmStatus310 copy) {
		super.set(copy);
		guidLights.set(copy.getGuidLights());
		devicePosition.set(copy.getDevicePosition());
		powerSaveRecoveryTime.set(copy.getPowerSaveRecoveryTime());
	}

	public List<Set<CdmGuidLights>> getGuidLights() {
		return guidLights.get();
	}

	public DevicePosition getDevicePosition() {
		return devicePosition.get();
	}

	public int getPowerSaveRecoveryTime() {
		return powerSaveRecoveryTime.get();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().appendSuper(super.hashCode()).append(getGuidLights()).append(getDevicePosition()).append(getPowerSaveRecoveryTime()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CdmStatus310) {
			CdmStatus310 cdmStatus310 = (CdmStatus310) obj;
			return new EqualsBuilder().appendSuper(super.equals(obj)).append(getGuidLights(), cdmStatus310.getGuidLights())
					.append(getDevicePosition(), cdmStatus310.getDevicePosition()).append(getPowerSaveRecoveryTime(), cdmStatus310.getPowerSaveRecoveryTime()).isEquals();
		}
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("guidLights", getGuidLights()).append("devicePosition", getDevicePosition())
				.append("powerSaveRecoveryTime", getPowerSaveRecoveryTime()).toString();
	}
}
