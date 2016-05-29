/*
 * Copyright (c) 2016, Andreas Fagschlunger. All rights reserved.
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

package at.o2xfs.xfs.cim.v3_00;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.cim.CashInType;
import at.o2xfs.xfs.win32.XfsDWordBitmask;

public class CashInType3 extends Struct {

	protected final USHORT number = new USHORT();
	protected final XfsDWordBitmask<CashInType> type = new XfsDWordBitmask<>(CashInType.class);
	protected final Pointer noteIDs = new Pointer();

	protected CashInType3() {
		add(number);
		add(type);
		add(noteIDs);
	}

	public CashInType3(Pointer p) {
		this();
		assignBuffer(p);
	}

	public CashInType3(CashInType3 copy) {
		this();
		allocate();
		set(copy);
	}

	protected void set(CashInType3 copy) {
		number.set(copy.getNumber());
		type.set(copy.getType());
		noteIDs.pointTo(new NoteIDs(copy.getNoteIDs()));
	}

	public int getNumber() {
		return number.get();
	}

	public Set<CashInType> getType() {
		return type.get();
	}

	public int[] getNoteIDs() {
		return new NoteIDs(noteIDs).get();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getNumber()).append(getType()).append(getNoteIDs()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CashInType3) {
			CashInType3 cashInType3 = (CashInType3) obj;
			return new EqualsBuilder().append(getNumber(), cashInType3.getNumber()).append(getType(), cashInType3.getType()).append(getNoteIDs(), cashInType3.getNoteIDs())
					.isEquals();
		}
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("number", getNumber()).append("type", getType()).append("noteIDs", getNoteIDs()).toString();
	}
}