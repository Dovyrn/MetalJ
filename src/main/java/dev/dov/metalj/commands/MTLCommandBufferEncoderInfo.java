package dev.dov.metalj.commands;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLCommandBufferEncoderInfo extends NSObject {
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
        return NSString.of(sendPtr(id, "label"));
    }

    public NSArray debugSignposts() {
        return NSArray.of(sendPtr(id, "debugSignposts"));
    }

    public long errorState() {
        return sendLong(id, "errorState");
    }
}
