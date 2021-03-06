/*
 * Copyright (c) 2018, Andreas Fagschlunger. All rights reserved.
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

package at.o2xfs.xfs.v3_00.ptr;

import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;

public class QueryField3 extends Struct {

	protected final LPSTR formName = new LPSTR();
	protected final LPSTR fieldName = new LPSTR();

	protected QueryField3() {
		add(formName);
		add(fieldName);
	}

	public QueryField3(Pointer p) {
		this();
		assignBuffer(p);
	}

	public QueryField3(QueryField3 copy) {
		this();
		allocate();
		set(copy);
	}

	protected void set(QueryField3 copy) {
		formName.set(copy.getFormName());
		Optional<String> fieldName = copy.getFieldName();
		if (fieldName.isPresent()) {
			this.fieldName.set(fieldName.get());
		}
	}

	public String getFormName() {
		return formName.get();
	}

	public Optional<String> getFieldName() {
		Optional<String> result = Optional.empty();
		if (!Pointer.NULL.equals(fieldName)) {
			result = Optional.of(fieldName.get());
		}
		return result;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getFormName()).append(getFieldName()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QueryField3) {
			QueryField3 queryField3 = (QueryField3) obj;
			return new EqualsBuilder()
					.append(getFormName(), queryField3.getFormName())
					.append(getFieldName(), queryField3.getFieldName())
					.isEquals();
		}
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("formName", getFormName())
				.append("fieldName", getFieldName())
				.toString();
	}
}
