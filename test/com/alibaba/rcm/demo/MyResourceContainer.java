/*
 * Copyright (c) 2020 Alibaba Group Holding Limited. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Alibaba designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

package demo;

import com.alibaba.rcm.Constraint;
import com.alibaba.rcm.internal.AbstractResourceContainer;

import java.util.ArrayList;
import java.util.List;

public class MyResourceContainer extends AbstractResourceContainer {

    public List<String> operations = new ArrayList<>();

    private boolean dead;

    @Override
    protected void attach() {
        super.attach();
        operations.add("attach");
    }

    @Override
    protected void detach() {
        super.detach();
        operations.add("detach");
    }

    @Override
    public State getState() {
        return dead ? State.DEAD : State.RUNNING;
    }

    @Override
    public void updateConstraint(Constraint constraint) {
        operations.add("update " + constraint.getResourceType());
    }

    @Override
    public Iterable<Constraint> getConstraints() {
        return null;
    }

    @Override
    public void destroy() {
        dead = true;
    }
}
