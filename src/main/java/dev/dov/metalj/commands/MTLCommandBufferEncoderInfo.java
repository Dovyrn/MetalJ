package dev.dov.metalj.commands;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLCommandBufferEncoderInfo extends NSObject {
    private static final long DEBUG_SIGNPOSTS = ObjC.sel("debugSignposts");
    private static final long ERROR_STATE = ObjC.sel("errorState");
    private static final long LABEL = ObjC.sel("label");

    public static final long MTLCommandEncoderErrorStateUnknown = 0;
    public static final long MTLCommandEncoderErrorStateCompleted = 1;
    public static final long MTLCommandEncoderErrorStateAffected = 2;
    public static final long MTLCommandEncoderErrorStatePending = 3;
    public static final long MTLCommandEncoderErrorStateFaulted = 4;

    private MTLCommandBufferEncoderInfo(long id) {
        super(id);
    }

    public static MTLCommandBufferEncoderInfo of(long id) {
        return new MTLCommandBufferEncoderInfo(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public NSArray debugSignposts() {
        return NSArray.of(sendPtr(id, DEBUG_SIGNPOSTS));
    }

    public long errorState() {
        return sendLong(id, ERROR_STATE);
    }
}
