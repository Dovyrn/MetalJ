package dev.dov.metalj.metal4.encoders;

import dev.dov.metalj.metal4.MTL4ArgumentTable;
import dev.dov.metalj.metal4.pipelines.MTL4MachineLearningPipelineState;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.heaps.MTLHeap;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4MachineLearningCommandEncoder extends MTL4CommandEncoder {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4MachineLearningCommandEncoder(long id) {
        super(id);
    }

    public static MTL4MachineLearningCommandEncoder of(long id) {
        return new MTL4MachineLearningCommandEncoder(id);
    }

    @SneakyThrows
    public void setPipelineState(MTL4MachineLearningPipelineState state) {
        P.invokeExact(id, ObjC.sel("setPipelineState:"), state.getId());
    }

    @SneakyThrows
    public void setArgumentTable(MTL4ArgumentTable table) {
        P.invokeExact(id, ObjC.sel("setArgumentTable:"), table.getId());
    }

    @SneakyThrows
    public void dispatchNetworkWithIntermediatesHeap(MTLHeap heap) {
        P.invokeExact(id, ObjC.sel("dispatchNetworkWithIntermediatesHeap:"), heap.getId());
    }
}
