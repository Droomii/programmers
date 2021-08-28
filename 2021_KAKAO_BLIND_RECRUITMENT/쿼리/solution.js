function solution(info, queries) {
    const answer = [];

    const db = info.reduce((acc, v) => {
        const split = v.split(' ');
        const score = parseInt(split.splice(-1)[0]);
        
        let obj = acc;
        split.forEach((v2, i) => {
            if (!obj[v2]) {
                if (i === split.length-1)
                    obj[v2] = [];
                else
                    obj[v2] = {};
            }
            obj = obj[v2];
        })
        obj.push(score);
        obj.sort((a, b) => a - b);
        return acc;
    }, {})

    return queries.map(query => {
        let sum = 0;

        const queryValues = query.split(' ').reduce((acc, v) => {
            if (v === 'and') return acc;
            acc.push(v);
            return acc;
        }, [])
        const queryScore = parseInt(queryValues.splice(-1)[0]);
    
        let nodes = [db];
        queryValues.forEach((v, i) => {
            if (v === '-') {
                nodes = nodes.reduce((acc, v) => {
                    acc.push(...Object.values(v));
                    return acc;
                }, [])
            } else {
                nodes = nodes.map(node => node[v]).filter(node => node);
            }
        })
        nodes.forEach(node => {
            sum += countGreaterOrEqual(node, node.length, queryScore);
        })
        return sum;
    })
}

function countGreaterOrEqual(arr, n, k) {
    var l = 0;
    var r = n - 1;
 
    var leftGreater = n;
 
    while (l <= r) {
        var m = l + parseInt((r - l) / 2);
        if (arr[m] >= k) {
            leftGreater = m;
            r = m - 1;
        }
        else
            l = m + 1;
    }
    return (n - leftGreater);
}
