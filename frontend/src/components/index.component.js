import React, {Component} from 'react';
import TableRow from './tableRaw.component';
import stockService from '../service/stock.service'

export default class Index extends Component {

    constructor(props) {
        super(props);
        this.state = {stocks: []};
    }

    // Retrieve list of stocks
    componentDidMount() {
        stockService.getStocksList().then(response => {
            this.setState({stocks: response.data});
        }).catch(function (error) {
            console.log(error);
        });
    }

    // Renders a row for each stock
    tabRow() {
        return this.state.stocks.map(function (object, i) {
            return <TableRow obj={object} key={i}/>;
        });
    }

    render() {
        return (
            <div>
                <h3 align="center">Stock List</h3>
                <table className="table table-striped" style={{marginTop: 20}}>
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th colSpan="2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.tabRow()}
                    </tbody>
                </table>
            </div>
        );
    }
}
