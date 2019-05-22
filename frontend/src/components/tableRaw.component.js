import React, {Component} from 'react';
import {Link} from 'react-router-dom';

// Component for table rendering of stock list
class TableRow extends Component {
    render() {
        return (
            <tr>
                <td>
                    {this.props.obj.id}
                </td>
                <td>
                    {this.props.obj.stockName}
                </td>
                <td>
                    {this.props.obj.stockPrice}
                </td>
                <td>
                    <Link to={"/edit/" + this.props.obj.id} className="btn btn-primary">Edit</Link>
                </td>
            </tr>
        );
    }
}

export default TableRow;
