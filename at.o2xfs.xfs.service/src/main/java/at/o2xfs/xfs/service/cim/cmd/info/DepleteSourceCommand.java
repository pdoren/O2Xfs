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

package at.o2xfs.xfs.service.cim.cmd.info;

import java.util.List;
import java.util.concurrent.Callable;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.cim.CimInfoCommand;
import at.o2xfs.xfs.v3_30.cim.DepleteInfo330;
import at.o2xfs.xfs.v3_30.cim.DepleteInfoResult330;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cim.CimFactory;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.service.cmd.XfsCallable;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;

public final class DepleteSourceCommand implements Callable<List<DepleteInfoResult330>> {

	private static final Logger LOG = LoggerFactory.getLogger(DepleteSourceCommand.class);

	private final CimService cimService;

	private final DepleteInfo330 depleteInfo;

	public DepleteSourceCommand(CimService cimService, DepleteInfo330 depleteInfo) {
		this.cimService = cimService;
		this.depleteInfo = depleteInfo;
	}

	@Override
	public List<DepleteInfoResult330> call() throws XfsException {
		List<DepleteInfoResult330> result;
		XfsInfoCommand<CimInfoCommand> command = new XfsInfoCommand<CimInfoCommand>(cimService,
				CimInfoCommand.DEPLETE_SOURCE, depleteInfo);
		WFSResult wfsResult = null;
		try {
			wfsResult = command.call();
			result = CimFactory.fromNullTerminatedArray(cimService.getXfsVersion(), wfsResult.getResults(),
					DepleteInfoResult330.class);
			if (LOG.isInfoEnabled()) {
				LOG.info("call()", result);
			}
		} finally {
			if (wfsResult != null) {
				XfsServiceManager.getInstance().free(wfsResult);
			}
		}
		return result;
	}
}
