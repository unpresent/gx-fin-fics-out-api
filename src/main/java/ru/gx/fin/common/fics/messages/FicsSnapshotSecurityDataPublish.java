package ru.gx.fin.common.fics.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.gx.core.messaging.*;
import ru.gx.fin.common.fics.config.FicsMessageTypes;
import ru.gx.fin.common.fics.out.Security;

import java.util.Arrays;

@SuppressWarnings("unused")
@ToString(callSuper = true)
public class FicsSnapshotSecurityDataPublish
        extends AbstractMessage<FicsSnapshotSecurityDataPublish.FicsSnapshotSecurityDataPublishBody> {
    public static final int V1 = 1;
    public static final int[] SUPPORTED_VERSIONS = {V1};

    private static boolean initialized = false;

    public static void staticInit() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.stream(SUPPORTED_VERSIONS).forEach(version ->
                MessageTypesRegistrator.registerType(
                        MessageKind.DataPublish,
                        FicsMessageTypes.Snapshots.SECURITIES,
                        version,
                        FicsSnapshotSecurityDataPublish.class,
                        FicsSnapshotSecurityDataPublishBody.class
                )
        );
    }

    @JsonCreator
    public FicsSnapshotSecurityDataPublish(
            @JsonProperty("header") @NotNull final StandardMessageHeader header,
            @JsonProperty("body") @NotNull final FicsSnapshotSecurityDataPublish.FicsSnapshotSecurityDataPublishBody body,
            @JsonProperty("correlation") final @Nullable MessageCorrelation correlation
    ) {
        super(header, body, correlation);
    }

    public static class FicsSnapshotSecurityDataPublishBody extends AbstractMessageBodyDataObject<Security> {
        @JsonCreator
        public FicsSnapshotSecurityDataPublishBody(
                @JsonProperty("dataObject") @NotNull final Security dataObject
        ) {
            super(dataObject);
        }
    }
}
