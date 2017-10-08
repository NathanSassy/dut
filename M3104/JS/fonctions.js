
function init(array) {
    'use strict';
    array.push([1, 47.644795, -2.776605]);
    array.push([2, 47.646870, -2.778911]);
    array.push([3, 47.646197, -2.780220]);
    array.push([4, 47.646992, -2.781068]);
    array.push([5, 47.647867, -2.781744]);
    array.push([6, 47.648510, -2.780145]);
}

function convertToRad(degrees) {
    return degrees * (Math.PI / 180);
};

function distance(lat1, long1, lat2, long2) {
    let REarth =  6378.137;
    return REarth * Math.acos(Math.sin(convertToRad(lat2))
        * Math.sin(convertToRad(lat1))
        + Math.cos(convertToRad(lat2))
        * Math.cos(convertToRad(lat1))
        * Math.cos(convertToRad(long2)
        - convertToRad(long1)));
};

function journeyDistance(coordinates) {
    let d = 0;
    for (let i = 0; i < coordinates.length - 1; i++) {
        d += distance(coordinates[i][1], coordinates[i][2], coordinates[i+1][1], coordinates[i+1][2]);
    }
    return d;
};

function main() {
    var coordinates = [];
    init(coordinates);
    
    let d = 0;
    for (let i = 0; i < coordinates.length - 1; i++) {
        d += distance(coordinates[i][1], coordinates[i][2], coordinates[i+1][1], coordinates[i+1][2]);
    }
    console.log("d = " + d);
}

main();