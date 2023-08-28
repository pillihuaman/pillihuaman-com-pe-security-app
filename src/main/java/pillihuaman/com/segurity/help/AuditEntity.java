package pillihuaman.com.segurity.help;


import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class AuditEntity implements Serializable {

    @BsonId
    private ObjectId id;

    @BsonProperty("codUser")
    private ObjectId codUser;


}
