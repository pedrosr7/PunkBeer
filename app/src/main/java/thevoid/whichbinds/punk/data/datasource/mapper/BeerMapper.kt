package thevoid.whichbinds.punk.data.datasource.mapper

import thevoid.whichbinds.punk.data.datasource.dto.NetworkBeer
import thevoid.whichbinds.punk.domain.models.Beer

fun NetworkBeer.toDomain() = Beer(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl ?: "",
    degree = degree
)
