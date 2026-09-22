package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLStructType extends MTLType {
    private static final long MEMBER_BY_NAME = ObjC.sel("memberByName:");
    private static final long MEMBERS = ObjC.sel("members");

    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);

    private MTLStructType(long id) {
        super(id);
    }

    public static MTLStructType of(long id) {
        return new MTLStructType(id);
    }

    public NSArray members() {
        return NSArray.of(sendPtr(id, MEMBERS));
    }

    @SneakyThrows
    public MTLStructMember memberByName(NSString name) {
        return MTLStructMember.of((long) P_P.invokeExact(id, MEMBER_BY_NAME, name.getId()));
    }
}
