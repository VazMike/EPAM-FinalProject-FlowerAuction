package com.viazovski.flowerauction.model.id;

import com.viazovski.flowerauction.model.Flower;

/**
 * {@code FlowerId} represents PK of Flower model
 */
public class FlowerId implements Id<Flower> {

    private int flowerId;

    public FlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public int getFlowerId() {
        return flowerId;
    }
}
