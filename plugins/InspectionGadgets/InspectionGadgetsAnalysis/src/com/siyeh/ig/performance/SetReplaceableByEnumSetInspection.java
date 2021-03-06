/*
 * Copyright 2003-2017 Dave Griffith, Bas Leijdekkers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.siyeh.ig.performance;

import com.intellij.psi.CommonClassNames;
import com.siyeh.InspectionGadgetsBundle;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SetReplaceableByEnumSetInspection extends BaseInspection {

  @Override
  @NotNull
  public String getDisplayName() {
    return InspectionGadgetsBundle.message("set.replaceable.by.enum.set.display.name");
  }

  @Override
  @NotNull
  protected String buildErrorString(Object... infos) {
    return InspectionGadgetsBundle.message("set.replaceable.by.enum.set.problem.descriptor");
  }

  @Override
  public BaseInspectionVisitor buildVisitor() {
    return new SetReplaceableByEnumSetVisitor();
  }

  private static class SetReplaceableByEnumSetVisitor extends CollectionReplaceableByEnumCollectionVisitor {

    @NotNull
    @Override
    protected List<String> getUnreplaceableCollectionNames() {
      return Arrays.asList("java.util.concurrent.CopyOnWriteArraySet", "java.util.concurrent.ConcurrentSkipListSet",
                           "java.util.LinkedHashSet");
    }

    @NotNull
    @Override
    protected List<String> getReplaceableCollectionNames() {
      return Collections.singletonList(CommonClassNames.JAVA_UTIL_HASH_SET);
    }

    @NotNull
    @Override
    protected String getReplacementCollectionName() {
      return "java.util.EnumSet";
    }

    @NotNull
    @Override
    protected String getBaseCollectionName() {
      return CommonClassNames.JAVA_UTIL_SET;
    }
  }
}
