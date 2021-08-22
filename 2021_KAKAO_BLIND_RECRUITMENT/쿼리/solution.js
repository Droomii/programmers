function solution(info, queries) {
    const answer = [];

    const db = info.reduce((acc, v) => {
        const split = v.split(' ');
        const score = parseInt(split.splice(-1)[0]);
        
        let obj = acc;
        split.forEach((v2, i) => {
            if (!obj[v2]) {
                if (i === 3)
                    obj[v2] = [];
                else
                    obj[v2] = {};
            }
            obj = obj[v2];
        })
        obj.push(score);
        obj.sort((a, b) => a - b);
        return acc;
    }, [])

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
            const idx = node.findIndex(v => v >= queryScore);
            if (idx < 0) return;
            sum += node.length - idx;
        })
        return sum;
    })
}
