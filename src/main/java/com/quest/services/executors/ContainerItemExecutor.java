package com.quest.services.executors;

import com.quest.commons.models.ContainerItemModel;
import com.quest.services.interfaces.Executable;
import com.quest.services.interfaces.Executor;
import com.quest.services.models.LocalItem;
import com.quest.services.models.SubActionModel;

public class ContainerItemExecutor implements Exec utor<ContainerItemModel<LocalItem, SubActionModel> {
    @Override
    public Executable execute(Object data, Executable command) {
        return null;
    }
}
