package com.filnm.nearby.util

import com.google.android.gms.maps.model.LatLng

fun findSouthweastPoint(points: List<LatLng>): LatLng {
    if (points.isEmpty()) return LatLng(0.0, 0.0)

    var southweastPoint = points[0]

    for (point in points) {
        if (
            point.latitude < southweastPoint.latitude ||
            point.latitude == southweastPoint.latitude && point.longitude < southweastPoint.longitude
        ) {
            southweastPoint = point
        }
    }

    return southweastPoint
}

fun findNorthweastPoint(points: List<LatLng>): LatLng {
    if (points.isEmpty()) return LatLng(0.0, 0.0)

    var northweastPoint = points[0]

    for (point in points) {
        if (
            point.latitude < northweastPoint.latitude ||
            point.latitude == northweastPoint.latitude && point.longitude < northweastPoint.longitude
        ) {
            northweastPoint = point
        }
    }

    return northweastPoint
}