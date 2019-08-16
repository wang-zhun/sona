/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/Apache-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package org.apache.spark.angel.ml.tree.stats.hash;

public class BKDRHash extends Int2IntHash {
    private int seed;

    public BKDRHash(int size, int seed) {
        super(size);
        this.seed = seed;
    }

    protected int doHash(int key) {
        int code = 0;
        while (key != 0) {
            code = seed * code + (key % 10);
            key /= 10;
        }
        return code;
    }

    @Override
    public Int2IntHash clone() {
        BKDRHash copy = new BKDRHash(size, seed);
        copy.buffer = this.buffer;
        return copy;
    }

    @Override
    public boolean equalsIgnoreSize(Int2IntHash other) {
        return other instanceof BKDRHash
                && ((BKDRHash) other).getSeed() == seed;
    }

    public int getSeed() {
        return seed;
    }
}
